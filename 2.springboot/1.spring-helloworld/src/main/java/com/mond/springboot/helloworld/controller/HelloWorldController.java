package com.mond.springboot.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
// 要使用RestController才能返回非json的数据
@RequestMapping("/hello")
public class HelloWorldController {
    @GetMapping("/hi")
    public String helloworld() {
        return "helloworld";
    }

}
