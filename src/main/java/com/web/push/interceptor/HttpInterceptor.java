package com.web.push.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class HttpInterceptor implements HandlerInterceptor {

  // private final log log = logFactory.getlog(HttpInterceptor.class);
  // private final Logger log = LoggerFactory.getLogger(this.getClass());

  // controller 실행전
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
          throws Exception {
      String logkey = UUID.randomUUID().toString().replace("-", "");
      log.info("========== [START] ==========");
      log.info("getRequestURL = {}", request.getRequestURL());
      log.info("getRequestURI = {}", request.getRequestURI());
      log.info("getMethod = {}", request.getMethod());
//      log.info("Authorization: {}", request.getHeader("Authorization"));
      return true;
  }

  // controller 실행후
  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
          ModelAndView modelAndView) throws Exception {

  }

  // view 실행 후
  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
          throws Exception {

      log.info("========== [END] ==========");
  }
}
