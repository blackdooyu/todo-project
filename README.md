# Springboot, React, Aws 를 사용한 Todo

rest-api의 장점인 서버와 클라이언트의 역할이 명확히 분리되는 것을 경험해보기 위해 제작해본 Toy Project 입니다.

제작한 Project는 Aws의 Elastic Beanstalk 를 이용하여 배포하였습니다.

회원가입은 OAuth2를 사용하여 구글,카카오 로그인에 성공 할 경우 이름과 Email 을 scope 로 요청하여
회원으로 저장하게 구현했습니다.

주소 : <a href="https://todo-list-project.xyz"> https://todo-list-project.xyz </a>


<h2 id="skill">기술 스택</h2>

---

 - Front-end : react

 - Back-end : spring boot, Spring Data Jpa , Query Dsl

 - DB : MySql

 - Cloud : Aws Elastic Beanstalk , Aws RDS


<h2> View Page </h2>

---

### 메인 페이지

카카오 , 구글 아이디로 로그인이 가능합니다. 
로그인에 성공하면 처음 로그인 했을 경우 Db에 이름 ,  Email 을 저장합니다.
그 이후 Session 을 발급하여 클라이언트가 누구인지 특정할 수 있는 Session Id를 클라이언트에게 보내줍니다.

![image](https://user-images.githubusercontent.com/84924161/161795123-01790230-36d4-46bf-8afb-313960a00024.png)


### Todo Page

날짜를 선택하여 해당 날짜의 Todo를 가져 올 수 있습니다.

왼쪽 체크 버튼을 누르면 Todo Status 를 변경하여 완료 , 미완료를 업데이트 할 수 있습니다.

또한 Todo의 삭제 또한 가능합니다.


![image](https://user-images.githubusercontent.com/84924161/161796427-6817873a-9c4e-4f96-b296-814a132dca6b.png)


하단의 + 버튼을 누르면 Todo 추가 가능합니다.

날짜를 선택하지 않으면 오늘 날짜로 추가 됩니다.

![image](https://user-images.githubusercontent.com/84924161/161797224-f5f751d8-2145-49dc-ba62-e818e467d40f.png)


<h2>프로젝트를 하며 느낀점</h2>

---

서버쪽 기능을 구현하는거에 대해서는 어려움이 없었지만

처음 사용해본 React,Aws 에서 많은 시간이 소용됐습니다.

특히 Aws는 처음에 프론트는 S3 + CloudFront를 사용하여 배포하였지만

다른 도메인끼리는 쿠키를 공유하지 못한다는걸 몰라서 며칠간 고생하였습니다..

본래의 목적인 서버와 클라이언트의 역할 분담의 장점은 충분히 느낀것 같아 의미 있었던 Toy Project 였습니다 😊
