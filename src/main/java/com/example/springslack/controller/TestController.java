package com.example.springslack.controller;

import com.example.springslack.dto.TestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by jg 2021/05/06
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("/")
    public String test() {
        log.error("Maru Spring Error log Test ~ing");
        log.info("Bobae is Babo");
        log.debug("MARU Server");
        return "test";
    }

    @PostMapping("/test")
    public TestDto testPost(@RequestBody TestDto testDto) {
        return testDto;
    }
}
