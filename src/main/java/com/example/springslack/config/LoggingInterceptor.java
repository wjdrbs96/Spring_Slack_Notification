package com.example.springslack.config;

import com.example.springslack.slack.SlackSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * created by jg 2021/05/06
 */
@RequiredArgsConstructor
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingInterceptor.class);

    private final ObjectMapper objectMapper;
    private final SlackSender slackSender;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //slackSender.sendSlack("RequestURL: " + request.getRequestURL().toString());
        if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) {
            return;
        }
        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
        final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;
        if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains("application/json")) {
            if (cachingRequest.getContentAsByteArray().length != 0) {
                slackSender.sendSlack("RequestBody: " + objectMapper.writeValueAsString(objectMapper.readTree(cachingRequest.getContentAsByteArray())));
                LOG.info("Request Body : {}", objectMapper.readTree(cachingRequest.getContentAsByteArray()));
            }
        }
        if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {
            if (cachingResponse.getContentAsByteArray().length != 0) {
                slackSender.sendSlack("ResponseBody: " + objectMapper.writeValueAsString(objectMapper.readTree(cachingResponse.getContentAsByteArray())));
                LOG.info("Response Body : {}", objectMapper.readTree(cachingResponse.getContentAsByteArray()));
            }
        }
    }
}

