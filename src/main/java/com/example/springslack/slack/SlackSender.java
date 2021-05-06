package com.example.springslack.slack;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.function.Consumer;

import static org.springframework.web.reactive.function.BodyInserters.fromFormData;

/**
 * created by jg 2021/05/06
 */
@RequiredArgsConstructor
@Component
public class SlackSender {

    private final Logger logger = LoggerFactory.getLogger(SlackSender.class);
    private final WebClient webClient;

    @Value("${notification.slack.webhook.url}")
    private String webhookUrl;

    @Value("${notification.slack.channel}")
    private String channel;

    @Value("${notification.slack.botName}")
    private String botName;

    @Value("${notification.slack.icon.emoji}")
    private String iconEmoji;

    @Value("${notification.slack.icon.url}")
    private String iconUrl;

    public void sendSlack(String contents) {
        try {
            SlackMessage slackMessage = new SlackMessage(contents, channel, botName, iconEmoji, iconUrl);
            String payload = new Gson().toJson(slackMessage);
           //RestTemplate restTemplate = new RestTemplate();
            //HttpHeaders headers = new HttpHeaders();
            //headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            //HttpEntity<String> entity = new HttpEntity<>(payload, headers);

            webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(payload)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            //restTemplate.postForEntity(webhookUrl, entity, String.class);
        } catch (Exception e) {
            logger.error("Unhandled Exception occurred while send slack. [Reason] : ", e);
        }
    }
}
