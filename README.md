## 원티드 프리온보딩 챌린지 백엔드 20 사전과제<Watned Market>

### ✔사용 기술
Java(1.8), Spring Boot 2.7.1, Spring Security 2.7.1, Spring Data JPA, MySQL(8.0)

### ✔API 명세서
[https://documenter.getpostman.com/view/24847016/2sA3XY7yD1](https://documenter.getpostman.com/view/24847016/2sA3XY7yD1)

### ✔ERD
![wantedMarket_ERD_최종](https://github.com/kst6294/wanted-preonboarding-challenge-backend-20/assets/148170765/f33a882b-9842-4e78-b775-717e0fbb6174)

<br>

### ✔구현 내용
- Spring Security, JWT를 활용한 로그인 구현
- 커스텀 예외 클래스 생성, 예외 핸들러 클래스 구현하여 예외 상황 이해하기 쉽게 구현
- 도메인별 응답 DTO를 구현하고, 공통 응답 DTO인 ApiResponse로 반환하여 클라이언트에게 일관된 구조의 응답 반환
- log.error 메시지를 통해 정확한 오류 발생 위치와 원인을 기록하여 문제 발생 위치 쉽게 파악 가능
- 상품 상태 : ON_SALE(판매 중), RESERVED(예약 중) COMPLETED(판매 완료)
  * 추가 판매가 가능한 수량이 남아있는 경우 : **ON_SALE**
  * 추가 판매가 불가능하고 현재 구매확정을 대기하고 있는 경우 : **RESERVED(예약 중)**
  * 모든 수량에 대해 모든 구매자가 모두 구매확정한 경우 : **COMLETED(판매 완료)**
- 주문 상태 : TRADING(거래 중), TRADE_APPROVE(거래 완료)
  * 상품 구매 후, 판매 승인 전 : **TRADING**
  * 상품 구매 후, 판매 승인 후 : **TRADE_APPROVE**

<br>
 
### ✔아쉬운 점
- 테스트 코드를 작성하는데 시간이 너무 오래 걸려 요구사항의 기능 구현들만 진행하였습니다.
- 상품 수량 차감을 **구매자가 상품 구매 했을 때 vs 판매자가 판매 승인을 했을 때** 어느 상황에 적용해야할지 고민이 많았습니다. 상품을 구매했을 때 수량 차감을 진행했지만, 만약 동시에 여러명의 구매자가 구매하거나 판매승인을 하지 않는다면 고려해야할 점이 생길것 같습니다.  
- 판매를 승인하지 않을 경우를 고려하지 못한 점, 판매 승인 기간을 정해놓고 수량, 상품 상태 변경을 반영했다면 좋았을 것 같습니다.

<br>

## 사전과제 요구사항
##### 1단계 (필수)
1. 제품 등록과 구매는 회원만 가능합니다. 
2. 비회원은 등록된 제품의 목록조회와 상세조회만 가능합니다. 
3. 등록된 제품에는 "제품명", "가격", "예약상태"가 포함되어야하고, 목록조회와 상세조회시에 예약상태를 포함해야합니다.
4. 제품의 상태는 "판매중", "예약중", "완료" 세가지가 존재합니다. 
5. 구매자가 제품의 상세페이지에서 구매하기 버튼을 누르면 거래가 시작됩니다. 
6. 판매자와 구매자는 제품의 상세정보를 조회하면 당사자간의 거래내역을 확인할 수 있습니다. 
7. 모든 사용자는 내가 "구매한 용품(내가 구매자)"과 "예약중인 용품(내가 구매자/판매자 모두)"의 목록을 확인할 수 있습니다.
8. 판매자는 거래진행중인 구매자에 대해 '판매승인'을 하는 경우 거래가 완료됩니다.


<br>

##### 2단계 (선택)
9. 제품에 수량이 추가됩니다. 제품정보에 "제품명", "가격", "예약상태", "수량"이 포함되어야합니다. 
10. 다수의 구매자가 한 제품에 대해 구매하기가 가능합니다. (단, 한 명이 구매할 수 있는 수량은 1개뿐입니다.)
11. 구매확정의 단계가 추가됩니다. 구매자는 판매자가 판매승인한 제품에 대해 구매확정을 할 수 있습니다. 
12. 거래가 시작되는 경우 수량에 따라 제품의 상태가 변경됩니다. 
    - 추가 판매가 가능한 수량이 남아있는 경우 - 판매중
    - 추가 판매가 불가능하고 현재 구매확정을 대기하고 있는 경우 - 예약중
    - 모든 수량에 대해 모든 구매자가 모두 구매확정한 경우 - 완료
13. "구매한 용품"과 "예약중인 용품" 목록의 정보에서 구매하기 당시의 가격 정보가 나타나야합니다. 
    - 예) 구매자 A가 구매하기 요청한 당시의 제품 B의 가격이 3000원이었고 이후에 4000원으로 바뀌었다 하더라도 목록에서는 3000원으로 나타나야합니다. 


##### 공통
0. Python이나 Java 기반의 프레임워크를 사용하시길 권장합니다. 
1. 구매취소는 고려하지 않습니다.
2. 요구사항에 모호한 부분이 많은게 맞습니다. 같은 요구사항에 대해 다양한 시각을 보여주세요. 
3. 검증이 필요한 부분에 대해 테스트코드를 작성해주세요.
4. 작성한 API에 대한 명세를 작성해주세요.
5. 개발과정에서 어려웠던 부분이나 예기치 못한 케이스가 있었다면 기록을 남겨주세요.
6. 다른분들의 PR을 보면서 리뷰를 해보세요. 궁금한점을 자유롭게 남기면서 서로의 의견을 주고 받아주세요! 
7. 요구사항을 잘 진행해주신 분들 중에서 추첨하여 선물을 드리겠습니다 :)


