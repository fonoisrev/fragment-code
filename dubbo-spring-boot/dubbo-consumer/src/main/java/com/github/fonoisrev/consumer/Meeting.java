package com.github.fonoisrev.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.fonoisrev.api.HelloService;
import org.springframework.stereotype.Component;

@Component
public class Meeting {
    
    @Reference
    private HelloService helloService;
    
    public void sayHello() {
        String wuhang = helloService.sayHello("wuhang");
        System.out.println(wuhang);
    }
}
