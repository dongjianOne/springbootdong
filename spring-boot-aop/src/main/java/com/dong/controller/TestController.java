package com.dong.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DongJian
 * @date Created in 2020/10/22 14:49
 * Utils: Intellij Idea
 * @description:
 * @version:1.0
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("test")
    public String test() {
        log.info("这是一个测试控制器");
        return "i love u";
    }
}
