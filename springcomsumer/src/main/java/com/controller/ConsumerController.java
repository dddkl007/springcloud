package com.controller;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.service.ServiceConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private ServiceConsumer serviceConsumer;

    @RequestMapping(value = "/consumer", method = RequestMethod.GET)
    public String consumer(){
        restTemplate.getForEntity("http://HELLO-SERVICE/hello", String.class);
        return "Hello world";
    }

    @RequestMapping(value = "/sayHello", method = RequestMethod.GET)
    public String sayHello(){
        return serviceConsumer.sayHello();
    }

    @RequestMapping(value = "/test4", method = RequestMethod.GET)
    public String test4(){
        return "";
    }

    @RequestMapping(value = "/cache", method = RequestMethod.GET)
    public String testcache(){
        HystrixRequestContext.initializeContext();
        return serviceConsumer.testcache("id","zzq");
    }
}
