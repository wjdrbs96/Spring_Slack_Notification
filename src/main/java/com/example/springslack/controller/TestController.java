package com.example.springslack.controller;

import com.example.springslack.dto.TestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by jg 2021/05/06
 */
@RestController
public class TestController {

    @PostMapping("/")
    public TestDto test(@RequestBody TestDto testDto) {
        return testDto;
    }
}
