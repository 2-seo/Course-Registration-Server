![Languages](https://img.shields.io/github/languages/count/harry-bro/Course-Registration-Server) ![Version](https://img.shields.io/badge/Version-1.0.0-green.svg) ![Language](https://img.shields.io/badge/Language-java-orange.svg) 

# Course-Registration-Server
##  개요
명지대학교 2020년 2학기 **객체지향적 사고와 프로그래밍** 수업의 기말 프로젝트로 제출한 프로젝트입니다.

1. 주요 기능
	* 수강신청 프로젝트로 *관리자로부터 발급 받은 아이디* 를 통해 강좌를 **장바구니**에 담거나 **수강신청**을 할 수 있습니다.
	* 다중 소켓 프로그래밍을 통하여 클라이언트와 1:1 통신이 아닌 **1:M 통신**으로 구현하였습니다.
	* 데이터베이스(Database)는 **PostgreSQL**을 이용하였습니다.
2. 기타 기능
	* [로그인](#Login)
	* [공지사항](#공지사항)
	* [강좌 관리](#강좌관리)
	* [문의 관리](#문의관리)
	* [학생 관리](#학생관리)
	* [관리자 관리](#관리자관리)
	* [서버 관리](#서버관리)
## Login
<p align="center">
	<img width="412" alt="login" src="https://user-images.githubusercontent.com/67419004/102226299-915f0800-3f2b-11eb-823c-6501662abe17.png">
</p>

- 관리자가 로그인할 수 있는 부분이며, 프로그램의 첫 화면이기도 합니다.

## Main
<p align="center">
	<img width="412" alt="main" src="https://user-images.githubusercontent.com/67419004/102226486-c79c8780-3f2b-11eb-9599-c1f2ccd5677e.png">
</p>

- 메뉴 구성으로는 공지사항, 강좌관리, 문의관리, 학생관리, 관리자관리, 서버관리로 구성되어 있습니다.

## 공지사항
<p align="center">
	<img width="712" alt="notice" src="https://user-images.githubusercontent.com/67419004/102226615-ed299100-3f2b-11eb-8ba3-c5bf8b79a5b1.png">
</p>

- 공지사항을 등록, 조회, 수정, 삭제할 수 있습니다.

## 강좌관리
<p align="center">
	<img width="712" alt="lectureMgt" src="https://user-images.githubusercontent.com/67419004/102226848-3c6fc180-3f2c-11eb-8f3b-d5bc250795b4.png">
</p>

- 강좌를 추가, 조회, 수정, 삭제할 수 있습니다.

## 문의관리
<p align="center">
	<img width="712" alt="inquire" src="https://user-images.githubusercontent.com/67419004/102227099-82c52080-3f2c-11eb-9748-fbcd4fffbdd6.png">
</p>

- 문의사항을 개개인별로 답변해줄 수 있습니다.
- 답변이 작성되기 이전에는 처리 결과가 답변대기 상태로 존재합니다.
- 답변이 작성되면 답변 완료 상태로 상태가 변합니다.
- 답변이 한번 작성되면 학생 측에서는 문의사항을 수정, 삭제할 수 없습니다.(답변이 작성되기 이전까지는 가능)


## 학생관리
<p align="center">
	<img width="712" alt="student" src="https://user-images.githubusercontent.com/67419004/102227169-9b353b00-3f2c-11eb-9751-4c22358194a4.png">
</p>

- 학생을 검색, 등록, 학생정보를 수정할 수 있습니다.
- 전공은 DB에 등록되어 있는 전공만 할당이 가능합니다.
- 학번은 자동으로 부여됩니다.

## 관리자관리
<p align="center">
	<img width="712" alt="mgt" src="https://user-images.githubusercontent.com/67419004/102227331-c750bc00-3f2c-11eb-9466-97a32e5a39e9.png">
</p>

- 관리자를 추가, 삭제할 수 있으며 등록된 관리자를 통해 로그인이 가능합니다.

## 서버관리
<p align="center">
	<img width="712" alt="serverMgt" src="https://user-images.githubusercontent.com/67419004/102227431-e7807b00-3f2c-11eb-9a39-6eb7792f7e34.png">
</p>

- 서버를 켜고 끌 수 있으며, 라벨(Label)과 버튼의 활성화 비활성화를 통해 상태를 확인할 수 있습니다.
- 유저가 서버에 접속하는 것을 로그로 확인할 수 있습니다.
- 로그인 실패, 성공 로그를 확인할 수 있습니다.
- 동시 사용자(concurrent users) 수를 확인할 수 있습니다.

## 한눈에보는 DB구조
![db](https://user-images.githubusercontent.com/67419004/102227598-18f94680-3f2d-11eb-8280-49fb0539cd52.png)


# 회고
## 아쉬운 점
DB를 설계할 때 프로젝트를 급하게 진행하다보니 아쉬운 점이 많다.
1. `basket`과 `cart`의 Entity가 중복 기능을 하고 있으며 그 중 하나는 **아예 사용을 하지 않고 있다..!**
2. `admin`과 `notice` 두 Entity가 다른 엔터티와 최소 한 개 이상의 관계를 가졌어야 하는데 독립적으로 존재하고 있다.
3. admin과 student로 독립적으로 나누지 않고 user라는 super type을 두거나, user에 role을 주어 구성했으면 더욱 좋았을 것이다.
4. 서버와 클라이언트 연결 코드부분에 중복 코드가 다수 존재하여 리팩토링이 필요하다.
