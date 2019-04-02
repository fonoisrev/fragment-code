package com.github.fonoisrev.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.fonoisrev.api.BigDecimalService;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Service(interfaceClass = BigDecimalService.class)
@Component
public class BigDecimalServiceImpl implements BigDecimalService {
    
    @Override
    public BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }
}
