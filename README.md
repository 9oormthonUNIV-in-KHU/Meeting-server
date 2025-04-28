# Meeting-server

![Image](https://github.com/user-attachments/assets/2c8e4fa5-7465-4b6b-bf21-226ccd494563)

![Image](https://github.com/user-attachments/assets/9e008b45-300d-47b2-8c1a-d4e04b7208f3)

## 💻 Convention 💻

## 🌲 Branch Convention 🌲
1. **기본 브랜치 설정**
    
    - dev: 기본 브랜치로, 기능을 개발하고 안정화된 브랜치
2. **작업 순서**
    
    1. 작업할 이슈 작성
    
    예) #111 사용자 로그인 기능 구현
    
    2. 작업 브랜치 생성
        - 기능 개발: feature/#[이슈번호]-title
            - ex) feature/#111-login
        - 버그 수정: fix/#[이슈번호]-title
            - ex) fix/#111-login
        - 리팩토링: refactor/#[이슈번호]-title
            - ex) refactor/#111-login
    3. **생성한 브랜치에서 작업 수행** 
    4. **원격 저장소에 작업 브랜치 푸시** 
    5. **Pull Request 생성**
    - develop 브랜치 대상으로 Pull Request 생성
    - 리뷰어의 리뷰를 받은 후 PR을 승인 받고 develop 브랜치에 병합 후 브랜치 삭제
---
## 🧑‍💻 Code Convention 🧑‍💻

**네이밍 규칙**

- **변수, 함수, 메소드 **: 카멜케이스 (예: userName)
- **클래스 , exception **: 파스칼케이스 (예: UserProfile)
**코딩스타일**
@Service, @Controller, @Repository 명확히 구분

DTO, Entity, Request/Response 객체 명시적으로 구분 (LoginRequest, LoginResponse)

Layer별 (Controller → Service → Repository) 호출 구조 유지


---
## 💬 Issue Convention 💬
1. **Feature**: 기능 추가 시 작성

2. **Fix/Bug**: 오류/버그 발생 시 작성
 
3. **Refactor**: 리팩토링 작업 시 작성

숫자만 잘 적어봐요
---
## 🫷 PR Convention 🫸

**🔗 관련 이슈**

연관된 이슈 번호를 적어주세요. (예: #123)

---

**📌 PR 요약**

PR에 대한 간략한 설명을 작성해주세요.

(예: 해당 변경 사항의 목적이나 주요 내용)

---

**📑 작업 내용**

작업의 세부 내용을 작성해주세요.

1. 작업 내용 1
2. 작업 내용 2
3. 작업 내용 3

---

**스크린샷 (선택)**

---

**💡 추가 참고 사항**

PR에 대해 추가적으로 논의하거나 참고해야 할 내용을 작성해주세요. (예: 변경사항이 코드베이스에 미치는 영향, 테스트 방법 등)

---
## 🙏 Commit Convention 🙏

- feature : 새로운 기능이 추가되는 경우
- fix : bug가 수정되는 경우
- docs :  문서에 변경 사항이 있는 경우
- style : 코드 스타일 변경하는 경우 (공백 제거 등)
- refactor : 코드 리팩토링하는 경우 (기능 변경 없이 구조 개선)
- design : UI 디자인을 변경하는 경우
