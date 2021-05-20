package com.example.springslack.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by jg 2021/05/20
 */
@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.error("==============");
        log.error("Request URI : " + request.getRequestURI());
        log.error("Query String : " + request.getQueryString());
        log.error("Request URL : " + request.getRequestURL());
        log.error("Request Method : " + request.getMethod());
        log.error("==============");
    }
}
