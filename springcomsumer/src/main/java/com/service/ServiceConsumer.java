package com.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;

@Service
public class ServiceConsumer {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "error")
    public String sayHello(){
        String hello = restTemplate.getForEntity("http://hello-service/hello", String.class).getBody();
        return hello + "Hystrix";
    }
    private String error(){
        return "error";
    }

    @HystrixCommand
    public Observable<String> test4(){
        return Observable.create(new Observable.OnSubscribe<String>(){
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String str = restTemplate.getForEntity("http://hello-service/hello", String.class).getBody();
                subscriber.onNext(str);
                subscriber.onCompleted();
            }
        });
    }

    @HystrixCommand
    @CacheResult
    public String testcache(@CacheKey String id, String name){
        String str = restTemplate.getForEntity("http://hello-service/hello", String.class).getBody();
        return str + name;
    }

    @HystrixCommand
    @CacheRemove(commandKey = "testcache")
    public String removeCache(@CacheKey String id){
        return "null";
    }
}
