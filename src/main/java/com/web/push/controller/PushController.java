package com.web.push.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.push.dto.RequestDto;
import com.web.push.service.WebPushService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class PushController {

  private final WebPushService service;

  @GetMapping("/hello")
  public ResponseEntity<?> hello() throws Exception {
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }
  /**
   * JSON 파라미터 체크용
   *
   * @param request
   * @param requestBody
   * @return
   * @throws Exception
   */
  @PostMapping("/v1/send")
  public ResponseEntity<?> sendWebPush(HttpServletRequest request, @RequestBody String requestBody) throws Exception {
    log.debug("Request body: {}", requestBody);
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }
  
  /**
   * 토큰 저장
   *
   * @param dto
   * @return
   * @throws Exception
   */
  @PostMapping("/v1/pushset")
  public ResponseEntity<?> pushSet(@RequestBody RequestDto dto) throws Exception {
    log.debug("dto = {}", dto);
    return new ResponseEntity<>(service.saveToken(dto), HttpStatus.OK);
  }
  
  /**
   * JSON 데이타를 받아서 처리
   *
   * @param dto
   * @return
   * @throws Exception
   */
  @PostMapping("/v1/send2")
  public ResponseEntity<?> sendWebPush2(@RequestBody RequestDto dto) throws Exception {
    log.debug("dto = {}", dto);
    return new ResponseEntity<>(service.sendWebPush2(dto), HttpStatus.OK);
  }
  /**
   * push 전체 발송
   *
   * @return
   * @throws Exception
   */
  @PostMapping("/v1/sendall")
  public ResponseEntity<?> sendPushAll() throws Exception {
    return new ResponseEntity<>(service.sendPushAll(), HttpStatus.OK);
  }

}