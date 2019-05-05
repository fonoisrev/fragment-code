package com.github.fonoisrev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {JtaAutoConfiguration.class})
//@EnableJpaRepositories(basePackages = {"com.github.fonoisrev.dao"})
@EntityScan(basePackages = "com.github.fonoisrev.dao.entity")
public class ShardingJdbcApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcApplication.class, args);
    }
    
}
