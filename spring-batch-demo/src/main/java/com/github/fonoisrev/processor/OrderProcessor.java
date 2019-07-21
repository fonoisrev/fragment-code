package com.github.fonoisrev.processor;

import com.alibaba.fastjson.JSON;
import com.github.fonoisrev.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class OrderProcessor implements ItemProcessor<Order, Order> {
    
    @Override
    public Order process(Order item) throws Exception {
        Order transformed = new Order();
        
        transformed.setTxnId(item.getTxnId().toLowerCase());
        transformed.setStatus(item.getStatus().toLowerCase());
        
        transformed.setAmount(item.getAmount());
        transformed.setIpAddress(item.getIpAddress());
    
        log.info("Transform order {} into {}", JSON.toJSONString(item),
                 JSON.toJSONString(transformed));
        return transformed;
    }
}
