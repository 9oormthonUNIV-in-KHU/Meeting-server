package org.groomUniv.meet.common.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {
    private Key key;

    @Value("${jwt.secret}")
    String secret;

    @Value("${jwt.access-expiration-time}")
    private long accessTokenExpirationTime;

    @Value("${jwt.refresh-expiration-time}")
    private long refreshTokenExpirationTime;

    //application.yml에 저장한 secret 값을 가져와서 key에 저장하기
    @PostConstruct      // 스프링 컨테이너가 빈을 생성하고, 모든 의존성 주입이 끝난 뒤 자동으로 호출되는 메서드, 주입된 값을 안전하게 사용 가능
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);    // secretKey (Base64로 인코딩된 문자열)를 디코딩하여 byte 배열로 변환
        key = Keys.hmacShaKeyFor(keyBytes);    // 디코딩된 byte 배열을 사용하여 HMAC-SHA 알고리즘에 맞는 암호화 키 생성
    }


    //Access Token 생성하기
    public String createAccessToken(String name, String authorities) {
        Date now = new Date();
        Date accessTokenExpiresIn = new Date(now.getTime() + accessTokenExpirationTime); // 1시간

        return Jwts.builder()     // JWT 토큰을 생성하는 빌더 패턴
                .setSubject(name)   // 토큰의 subject를 인증된 사용자 이름으로 설정
                .claim("auth", authorities)     // auth 라는 키로 사용자의 권한 정보 저장
                .setExpiration(accessTokenExpiresIn)    // 만료시간 설정(1시간)
                .signWith(key, SignatureAlgorithm.HS256)    // HS256 알고리즘을 이용해 서명
                .compact();     //토큰을 하나의 문자열로 생성
    }


    //Refresh Token 생성하기
    public String createRefreshToken(String name) {
        Date now = new Date();
        Date refreshTokenExpiresIn = new Date(now.getTime() + refreshTokenExpirationTime);

        return Jwts.builder()
                .setSubject(name)
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    //Member 정보를 가지고 Access Token, Refresh Token 생성하기
    public JwtToken generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()   //UserDetails에서 권한 정보 가져오기
                .map(GrantedAuthority::getAuthority)    // 권한을 String으로 추출
                .collect(Collectors.joining(", ")); // ","로 구분된 문자열로 결합

        String name = authentication.getName();

        return new JwtToken("Bearer", createAccessToken(name, authorities), createRefreshToken(name));
    }


    //JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내기
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);   //Jwt 토큰을 복호화하여 claims에 저장

        if(claims.get("auth") == null) {    // claims의 auth가 null이면 예외 throw
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // claims를 확인하여 권한 정보 가져오기
        List<GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(",")) //auth 클레임을 가져와 쉼표로 구분된 권한 리스트 생성
                        .map(SimpleGrantedAuthority::new)   // 각 권한 문자열을 SimpleGrantedAuthority 객체로 변환
                        .collect(Collectors.toList());  // 변환된 권한들을 List로 수집하여 authorities에 저장

        UserDetails principal = new User(claims.getSubject(), "",authorities);

        // principal은 사용자 정보, authorities는 사용자의 권한 정보를 포함
        return new UsernamePasswordAuthenticationToken(principal, "", authorities); // 인증 객체 반환
    }


    //토큰 유효성 검사
    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key)     //Jwt 서명 검증에 사용할 키 설정
                    .build()
                    .parseClaimsJws(token); // 토큰을 파싱하고 유효성 검증(서명, 구조, 만료 등 포함)
            return true; // 예외가 발생하지 않으면 유효한 토큰

        }catch(SecurityException | MalformedJwtException exception){    // 서명오류 또는 구조상 잘못된 토큰
            log.info("Invalid JWT token", exception);
        }catch (ExpiredJwtException exception){     // 토큰이 만료
            log.info("Expired JWT token", exception);
        }catch (UnsupportedJwtException exception){     //지원하지 않는 형식의 토큰
            log.info("Unsupported JWT token", exception);
        }catch (IllegalArgumentException exception){    // 토큰이 비어있거나 null
            log.info("JWT claims string is empty", exception);
        }
        return false;   // 예외가 발생하면 유효하지 않은 토큰
    }


    //accessToken을 파싱하여 claims를 추출하기
    private Claims parseClaims(String token) {
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch (ExpiredJwtException exception){     // 토큰이 만료되었을 경우 예외에서 claims만 추출하여 반환
            return exception.getClaims();
        }
    }

    public String getUsername(String refreshToken) {
        return parseClaims(refreshToken).getSubject();
    }
}
