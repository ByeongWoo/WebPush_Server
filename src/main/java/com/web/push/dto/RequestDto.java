package com.web.push.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 서버에서 토큰값을 받아서 처리한다.
 * @author dayworker
 *
 */
@Data
@NoArgsConstructor
public class RequestDto {
    
  private String endpoint;
  private Long expirationTime;
  private Keys keys;

  @Data
  public static class Keys {
      private String p256dh;
      private String auth;
  }
}
