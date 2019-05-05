package com.github.fonoisrev.sharding.algorithm;

import com.alibaba.fastjson.JSON;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;

@Slf4j
public class TableShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    
    
    @Override
    public String doSharding(
            Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        log.info("availableTargetNames={}, shardingValue={}",
                 JSON.toJSONString(availableTargetNames), JSON.toJSONString(shardingValue));
        String payReqId = shardingValue.getValue();
        String logicTableName = shardingValue.getLogicTableName();
        
        int length = payReqId.length();
        int last = length - 1;
        char lastChar = payReqId.charAt(last);
        int suffix = lastChar % 2; // 0 or 1
    
        String actualTableName = logicTableName + "_" + suffix;
        log.info("actualTableName={}", actualTableName);
        return actualTableName;
    }
}
