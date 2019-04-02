package com.github.fonoisrev.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.fonoisrev.api.BigDecimalService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BigDecimalOperation {
    
    @Reference
    BigDecimalService bigDecimalService;
    
    public void doAdd() {
        System.out.println(bigDecimalService.add(new BigDecimal("1.10"), new BigDecimal("0.22")));
    }
}
