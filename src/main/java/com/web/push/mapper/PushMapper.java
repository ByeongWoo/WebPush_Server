package com.web.push.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.web.push.model.WebPush;


@Mapper
public interface PushMapper {
  
  public int getWebpush_seq();
  
  public void insertPush(WebPush push);
  
  public List<WebPush> getWebPushAll();
  
  public int getWebPush(String auth);

}
