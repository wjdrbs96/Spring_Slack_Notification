package com.example.springslack.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by jg 2021/05/20
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.error("==============");
        log.error("Query String : " + request.getQueryString() + " || Request URL : " + request.getRequestURL() + " || Request Method : " + request.getMethod());
        log.error("==============");

        if (request.getMethod().equals("POST")) {
            final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
            final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
            log.error(
                    String.format("Request Body : %s", objectMapper.readTree(cachingRequest.getContentAsByteArray())),
                    String.format("Response Body : %s", objectMapper.readTree(cachingResponse.getContentAsByteArray()))
            );
        }
    }
}
