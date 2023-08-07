# 웹 푸시
 - 웹 푸시 서버 코드
 
## 환경
 - springboot2.3.2 + java1.8 + mybatis + postgresql
 
## 핵심 코드 : 
 - com/web/push/controller/PushController.java 
 - com/web/push/service/WebPushService.java

## API 
 - /api/v1/send2 웹 푸시 단건 발송
 - /api/v1/pushset 토큰 저장
 - /api/v1/sendall DB에서 조회한 데이터 웹푸시 발송

## 관련 API
 - pom.xml 참고
 - nl.martijndwars
 - org.bouncycastle
 
## 참고 사이트
 - https://geundung.dev/114
 - https://wonsss.github.io/PWA/web-push-notification/ 
 - https://www.youtube.com/watch?v=2zHqTjyfIY8
 - https://joshua-dev-story.blogspot.com/2020/11/pwa-installable.html
 - https://web-push-book.gauntface.com/demos/notification-examples/
 
## DDL
 - CREATE TABLE wrap.webpush (
 -  seq int8 NOT NULL,
 -  endpoint varchar(500) NOT NULL,
 -  p256dh varchar(255) NOT NULL,
 -  auth varchar(100) NOT NULL
 - );
 
 