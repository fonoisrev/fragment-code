package com.github.fonoisrev.controller;

import com.alibaba.fastjson.JSON;
import com.github.fonoisrev.dao.OrderInfoRepository;
import com.github.fonoisrev.dao.entity.OrderInfo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    
    @Autowired
    OrderInfoRepository orderInfoRepository;
    
    @RequestMapping(path = "/order", method = RequestMethod.POST)
    public String addOrder(@RequestParam String payReqId) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPayReqId(payReqId);
        orderInfoRepository.save(orderInfo);
        return payReqId + " add success";
    }
    
    @RequestMapping(path = "/order_query", method = RequestMethod.POST)
    public String queryOrder(@RequestParam String payReqId) {
        OrderInfo topByPayReqId = orderInfoRepository.findTopByPayReqId(payReqId);
        return JSON.toJSONString(topByPayReqId);
    }
}
