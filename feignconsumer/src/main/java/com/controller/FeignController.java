package com.controller;

import com.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/sayhello", method = RequestMethod.GET)
    public String sayHello(){
        return helloService.hello();
    }
}
