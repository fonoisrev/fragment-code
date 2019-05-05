package com.github.fonoisrev.sharding.algorithm;

import com.alibaba.fastjson.JSON;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
public class DatabaseShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    
    @Override
    public String doSharding(
            Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        log.info("availableTargetNames={}, shardingValue={}",
                 JSON.toJSONString(availableTargetNames), JSON.toJSONString(shardingValue));
        String payReqId = shardingValue.getValue();
        String logicDatasourceName = "ds";
        
        int length = payReqId.length();
        int secondToLast = length - 2;
        char secondToLastChar = payReqId.charAt(secondToLast);
        int suffix = secondToLastChar % 2; // 0 or 1
    
        String actualDatasource = logicDatasourceName + suffix;
        log.info("actualDatasource={}", actualDatasource);
        return actualDatasource;
    }
}
