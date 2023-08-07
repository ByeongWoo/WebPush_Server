package com.web.push.service;

import java.security.Security;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.simple.JSONObject;
//import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.web.push.api.BaseResult;
import com.web.push.api.ResultCode;
import com.web.push.dto.RequestDto;
import com.web.push.mapper.PushMapper;
import com.web.push.model.WebPush;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;

/**
 *
 * @author
 * @date
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class WebPushService {
  
  private final PushMapper pushMapper;

  @Value("${push.publicKey}")
  private String publicKey;

  @Value("${push.privateKey}")
  private String privateKey;
  
  /**
   * 토큰 저장
   *
   * @param dto
   * @return
   * @throws Exception
   */
  public BaseResult saveToken(RequestDto dto) throws Exception{
    
    BaseResult result = new BaseResult(LocalDateTime.now(), ResultCode.SUCCESS);
    
    log.debug("getEndpoint = {}", dto.getEndpoint());
    log.debug("getP256dh = {}", dto.getKeys().getP256dh());
    log.debug("getAuth = {}", dto.getKeys().getAuth());
    
    int auth = pushMapper.getWebPush(dto.getKeys().getAuth());
    
    if(auth > 0) {
      log.debug("토큰 정보 이미 존재 auth = {}",auth);
      return result;  
    }

    int seq = pushMapper.getWebpush_seq();
    
    WebPush push = new WebPush();
    push.setSeq(seq);
    push.setEndpoint(dto.getEndpoint());
    push.setP256dh(dto.getKeys().getP256dh());
    push.setAuth(dto.getKeys().getAuth());
    
    pushMapper.insertPush(push);
    
    return result;  
    
  }
  
  /**
   * 푸시 전송
   * 한건씩 전송할 때 사용 
   * 클라이언트에서 전송한 토큰값으로 전송
   *
   * @param dto
   * @return
   * @throws Exception
   */
  public BaseResult sendWebPush2(RequestDto dto) throws Exception {
    // PushService 클래스 내부에서 사용되는 암호화 기능에 필요한 Bouncy Castle 프로바이더 등록을 위해 사용
    Security.addProvider(new BouncyCastleProvider());
    
    log.debug("getEndpoint = {}", dto.getEndpoint());
    log.debug("getExpirationTime = {}", dto.getExpirationTime());
    log.debug("getP256dh = {}", dto.getKeys().getP256dh());
    log.debug("getAuth = {}", dto.getKeys().getAuth());
    log.debug("publicKey = {}", publicKey);
    log.debug("privateKey = {}", privateKey);
    
    JSONObject jsonObject = new JSONObject();
    
    // payload 값 설정
    jsonObject.put("title", "일일일일일이이이이이삼삼삼삼삼사사사사사오오오오오육육육육육칠칠칠칠칠팔팔팔팔팔구구구구구십십십십십");
    jsonObject.put("body", "일일일일일이이이이이삼삼삼삼삼사사사사사오오오오오육육육육육칠칠칠칠칠팔팔팔팔팔구구구구구십십십십십");
    jsonObject.put("image", "https://i.pinimg.com/564x/00/82/bf/0082bf0dd5f91203d830712dadbf6ade.jpg");
    //jsonObject.put("icon", "https://venerable-cascaron-63d5c2.netlify.app/img/cat.png");
    
    String payload = jsonObject.toString();
    
    //String mailSubject = "mailto:idayworker@wrapcore.co.kr";
    String mailSubject = "https://venerable-cascaron-63d5c2.netlify.app/";
    
    BaseResult result = null;

    try {
      // Subscription 생성
      Subscription subscription = new Subscription(dto.getEndpoint(), new Subscription.Keys(dto.getKeys().getP256dh(), dto.getKeys().getAuth()));

      // PushService 생성
      PushService pushService = new PushService(publicKey, privateKey, mailSubject);
      
      // Notification 생성
      Notification notification = new Notification(subscription, payload);

      // 푸시 전송
      HttpResponse response = pushService.send(notification);
      log.debug("response = {}", response);
      
      //response = HTTP/1.1 201 Created [Location: https://fcm.googleapis.com/0:1688428713090698%e609af1cf9fd7ecd, X-Content-Type-Options: nosniff, X-Frame-Options: SAMEORIGIN, X-Xss-Protection: 0, Date: Mon, 03 Jul 2023 23:58:33 GMT, Content-Length: 0, Content-Type: text/html; charset=UTF-8, Alt-Svc: h3=":443"; ma=2592000,h3-29=":443"; ma=2592000] [Content-Length: 0,Chunked: false]
      if (response.getStatusLine().getStatusCode() == 201) {
        result = new BaseResult(LocalDateTime.now(), ResultCode.SUCCESS);
      } else {
        result = new BaseResult(LocalDateTime.now(), ResultCode.SUCCESS_FAIL);
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return result;  

  }
  /**
   * 푸시 전체 전송
   * db에서 선택한 토큰값으로 전송
   * 
   *
   * @return
   * @throws Exception
   */
  public BaseResult sendPushAll() throws Exception {
    // PushService 클래스 내부에서 사용되는 암호화 기능에 필요한 Bouncy Castle 프로바이더 등록을 위해 사용
    Security.addProvider(new BouncyCastleProvider());
    
    List<WebPush> list = new ArrayList<>(); 
    
    list = pushMapper.getWebPushAll();
    
    JSONObject jsonObject = new JSONObject();
    
    // payload 값 설정
    jsonObject.put("title", "일일일일일이이이이이삼삼삼삼삼사사사사사오오오오오육육육육육칠칠칠칠칠팔팔팔팔팔구구구구구십십십십십");
    jsonObject.put("body", "일일일일일이이이이이삼삼삼삼삼사사사사사오오오오오육육육육육칠칠칠칠칠팔팔팔팔팔구구구구구십십십십십일일일일일이이이이이삼삼삼삼삼사사사사사오오오오오육육육육육칠칠칠칠칠팔팔팔팔팔구구구구구십십십십십일일일일일이이이이이삼삼삼삼삼사사사사사오오오오오육육육육육칠칠칠칠칠팔팔팔팔팔구구구구구십십십십십일일일일일이이이이이삼삼삼삼삼사사사사사오오오오오육육육육육칠칠칠칠칠팔팔팔팔팔구구구구구십십십십십일일일일일이이이이이삼삼삼삼삼사사사사사오오오오오육육육육육칠칠칠칠칠팔팔팔팔팔구구구구구십십십십십일일일일일이이이이이삼삼삼삼삼사사사사사오오오오오육육육육육칠칠칠칠칠팔팔팔팔팔구구구구구십십십십십일일일일일이이이이이삼삼삼삼삼사사사사사오오오오오육육육육육칠칠칠칠칠팔팔팔팔팔구구구구구십십십십십일일일일일이이이이이삼삼삼삼삼사사사사사오오오오오육육육육육칠칠칠칠칠팔팔팔팔팔구구구구구십십십십십일일일일일이이이이이삼삼삼삼삼사사사사사오오오오오육육육육육칠칠칠칠칠팔팔팔팔팔구구구구구십십십십십일일일일일이이이이이삼삼삼삼삼사사사사사오오오오오육육육육육칠칠칠칠칠팔팔팔팔팔구구구구구십십십십십일일일일일이이이이이삼삼삼삼삼사사사사사오오오오오육육육육육칠칠칠칠칠팔팔팔팔팔구구구구구십십십십십");
    jsonObject.put("image", "https://i.pinimg.com/564x/00/82/bf/0082bf0dd5f91203d830712dadbf6ade.jpg");
    // jsonObject.put("icon", "https://venerable-cascaron-63d5c2.netlify.app/img/cat.png");
    
    String payload = jsonObject.toString();
    
    //String mailSubject = "mailto:idayworker@wrapcore.co.kr";
    String mailSubject = "https://venerable-cascaron-63d5c2.netlify.app/";

    try {
      for(WebPush push : list) {
        // Subscription 생성
        Subscription subscription = new Subscription(push.getEndpoint(), new Subscription.Keys(push.getP256dh(), push.getAuth()));
  
        // PushService 생성
        PushService pushService = new PushService(publicKey, privateKey, mailSubject);
        
        // Notification 생성
        Notification notification = new Notification(subscription, payload);
  
        // 푸시 전송
        HttpResponse response = pushService.send(notification);
        log.debug("response = {}", response);
        
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    BaseResult result = new BaseResult(LocalDateTime.now(), ResultCode.SUCCESS);
    return result;  

  }

}
