package com.github.fonoisrev;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.github.fonoisrev.consumer.BigDecimalOperation;
import com.github.fonoisrev.consumer.Meeting;
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
    
    @Bean
    public CommandLineRunner runner(BigDecimalOperation operation) {
        return (args) -> {
            operation.doAdd();
        };
    }
}
