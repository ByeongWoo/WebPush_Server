package com.web.push.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WebPush {
  
  private int seq;
  private String endpoint;
  private String p256dh;
  private String auth;
}
