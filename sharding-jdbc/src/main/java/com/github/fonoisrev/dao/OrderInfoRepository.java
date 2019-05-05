package com.github.fonoisrev.dao;

import com.github.fonoisrev.dao.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Integer> {
    
    OrderInfo findTopByPayReqId(String payReqId);
    
}
