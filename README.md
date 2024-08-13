## Wanted Market API
Market이라는 주제로 원티드 인턴십에서 진행한 백엔드 개인 프로젝트 입니다.

### ✔사용 기술
Java(1.8), Spring Boot 2.7.1, Spring Security 2.7.1, Spring Data JPA, MySQL(8.0)

### ✔진행 기간
2024.06.08 ~2024.06.22(2주)

### ✔API 명세서
[https://documenter.getpostman.com/view/24847016/2sA3XY7yD1](https://documenter.getpostman.com/view/24847016/2sA3XY7yD1)

### ✔ERD
![wantedMarket_ERD_최종](https://github.com/kst6294/wanted-preonboarding-challenge-backend-20/assets/148170765/f33a882b-9842-4e78-b775-717e0fbb6174)

<br>

### ✔구현 내용
### 1️⃣ 회원가입
- 회원가입 시 비밀번호 암호화 적용

### 2️⃣ 로그인
- Spring Security, JWT를 활용한 로그인 구현

### 3️⃣ 주문에 따른 상품 및 주문 상태
- 상품 상태 : ON_SALE(판매 중), RESERVED(예약 중) COMPLETED(판매 완료)
  * 추가 판매가 가능한 수량이 남아있는 경우 : **ON_SALE**
  * 추가 판매가 불가능하고 현재 구매확정을 대기하고 있는 경우 : **RESERVED(예약 중)**
  * 모든 수량에 대해 모든 구매자가 모두 구매확정한 경우 : **COMLETED(판매 완료)**
- 주문 상태 : TRADING(거래 중), TRADE_APPROVE(거래 완료)
  * 상품 구매 후, 판매 승인 전 : **TRADING**
  * 상품 구매 후, 판매 승인 후 : **TRADE_APPROVE**

### 4️⃣ 예외 처리 및 예외 발생시 모니터링
- 각 도메인별 커스텀 예외 클래스 생성, 예외 핸들러 클래스 구현하여 예외 상황 이해하기 쉽게 구현
- log 메시지를 통해 정확한 오류 발생 위치와 원인을 기록하여 문제 발생 위치 쉽게 파악 가능

### 5️⃣ 응답 DTO
- 도메인별 응답 DTO를 구현하고, 공통 응답 DTO인 ApiResponse로 반환하여 클라이언트에게 일관된 구조의 응답 반환


<br>
 
### ✔아쉬운 점
- 테스트 코드를 작성하는데 시간이 너무 오래 걸려 요구사항의 기능 구현들만 진행하였습니다.
- 상품 수량 차감을 **구매자가 상품 구매 했을 때 vs 판매자가 판매 승인을 했을 때** 어느 상황에 적용해야할지 고민이 많았습니다. 상품을 구매했을 때 수량 차감을 진행했지만, 만약 동시에 여러명의 구매자가 구매하거나 판매승인을 하지 않는다면 고려해야할 점이 생길것 같습니다.  
- 판매를 승인하지 않을 경우를 고려하지 못한 점, 판매 승인 기간을 정해놓고 수량, 상품 상태 변경을 반영했다면 좋았을 것 같습니다.


