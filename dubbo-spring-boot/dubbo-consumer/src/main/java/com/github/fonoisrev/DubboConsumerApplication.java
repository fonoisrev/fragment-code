package com.github.fonoisrev;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.github.fonoisrev.api.HelloService;
import com.github.fonoisrev.consumer.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDubboConfiguration
public class DubboConsumerApplication {
    
    
    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner runner(Meeting meeting) {
        return (args) -> {
            meeting.sayHello();
        };
    }
}
