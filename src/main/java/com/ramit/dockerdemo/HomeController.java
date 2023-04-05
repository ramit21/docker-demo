package com.ramit.dockerdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Value("${prop.env}")
    private String env;

    @RequestMapping(value = "/")
    @ResponseBody
    public String hello() {
        return "Hello World from env = " + env;
    }

}
