package com.example.springslack.slack;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * created by jg 2021/05/06
 */
@Builder
@Getter
@NoArgsConstructor
public class SlackMessage {

    private String text;

    private String channel;

    private String botName;

    private String iconEmoji;

    private String iconUrl;

    public SlackMessage(String text, String channel, String botName, String iconEmoji, String iconUrl) {
        this.text = text;
        this.channel = channel;
        this.botName = botName;
        if (StringUtils.hasText(iconEmoji)) {
            this.iconEmoji = iconEmoji;
        }
        if (StringUtils.hasText(iconUrl)) {
            this.iconUrl = iconUrl;
        }
    }
}
