package com.example.springslack.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * created by jg 2021/05/06
 */
@Configuration
public class SlackConfig {

    @Value("${notification.slack.webhook.url}")
    private String webhookUrl;

    @Bean
    public WebClient slackWebclient() {
        return WebClient.builder()
                .baseUrl(webhookUrl)
                .build();
    }
}
