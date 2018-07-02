package top.shanbing.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

@RestController
public class TestController {

    @Value("${test}")
    private String test;

    @RequestMapping("/")
    public @ResponseBody  String hello(){
        String datetime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        System.out.println(test+":"+datetime);
        return test+":"+datetime;
    }
}
