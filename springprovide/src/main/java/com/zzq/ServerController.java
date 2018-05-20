package com.zzq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class ServerController {
    private Logger log = Logger.getLogger("server:");

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String server(){
        try {
            List<ServiceInstance> instances = client.getInstances("hello-service");
            for(int i=0;i<instances.size();i++){
                log.info("/hello,host="+instances.get(i).getHost() +",serviceid="+instances.get(i).getServiceId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Hello word";
    }


}
