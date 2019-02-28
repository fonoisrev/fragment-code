package com.github.fonoisrev.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.fonoisrev.api.HelloService;
import org.springframework.stereotype.Component;

@Service(interfaceClass = HelloService.class)
@Component
public class MyHelloService implements HelloService {
    
    public String sayHello(String hello) {
        return "Hello world! " + hello;
    }
}
