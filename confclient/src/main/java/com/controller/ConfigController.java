package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {
//    @Value("${sang}")
    private String sang;
    @Autowired
    private Environment env;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getInfo(){
        String str = sang + env.getProperty("sang","未定义");
        return str;
    }

}
