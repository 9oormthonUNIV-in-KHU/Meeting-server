# Meeting-server

![Image](https://github.com/user-attachments/assets/2c8e4fa5-7465-4b6b-bf21-226ccd494563)

![Image](https://github.com/user-attachments/assets/9e008b45-300d-47b2-8c1a-d4e04b7208f3)

💻 Convention 💻
🌲 Branch Convention 🌲
기본 브랜치 설정

dev: 기본 개발 브랜치 (기능 개발 및 통합 테스트용)

main: 최종 배포 브랜치 (운영 반영)

작업 순서

작업할 이슈 생성 (Jira, GitHub Issues 등)

예: #111 사용자 로그인 API 구현

작업 브랜치 생성

기능 개발: feature/#이슈번호-title

ex) feature/#111-login-api

버그 수정: fix/#이슈번호-title

ex) fix/#111-login-nullpoint

리팩토링: refactor/#이슈번호-title

ex) refactor/#111-optimize-query

생성한 브랜치에서 기능 개발 및 커밋

원격 저장소에 작업 브랜치 Push

Pull Request 생성

dev 브랜치 대상으로 PR 생성

리뷰 승인 후 dev 브랜치에 Merge

Merge 완료되면 브랜치 삭제

🧑‍💻 Code Convention 🧑‍💻
네이밍 규칙 (Spring에 맞게)

클래스명: 파스칼케이스 (UserService, LoginController, MemberRepository)

메소드명: 카멜케이스 (findUserById, savePost, updateProfile)

변수명: 카멜케이스 (userName, userId, postList)

패키지명: 모두 소문자, 복수형 사용 X (controller, service, repository, domain)

상수명: 전부 대문자 + 언더스코어 (MAX_SIZE, DEFAULT_TIMEOUT)

코딩 스타일

@Service, @Controller, @Repository 명확히 구분

DTO, Entity, Request/Response 객체 명시적으로 구분 (LoginRequest, LoginResponse)

Layer별 (Controller → Service → Repository) 호출 구조 유지

예외(Exception)는 커스텀 Exception 클래스를 만들어 사용 (UserNotFoundException)

💬 Issue Convention 💬
Issue 종류

✅ Feature (기능 추가)

🐞 Fix (버그 수정)

♻️ Refactor (리팩토링)

📚 Docs (문서 작성 및 수정)

⚙️ Chore (빌드 설정, 환경 설정 등)

Issue 작성 템플릿

제목: [타입] 이슈 제목

내용: 이슈 상세 설명

TODO: 해야 할 작업 체크리스트

ETC: 논의가 필요한 내용

🫷 PR Convention 🫸
🔗 관련 이슈

연관된 이슈 번호 (#111) 링크

📌 PR 요약

PR에 대한 간단한 요약

ex) "로그인 API 구현 및 인증 예외 처리 추가"

📑 작업 내용

작업한 세부사항 리스트업

회원가입 기능 추가

로그인 검증 로직 수정

로그인 실패시 에러 반환 추가

🖼️ 스크린샷 (선택)

💡 추가 참고 사항

PR에 대한 논의사항

로직 설명이 필요한 부분

