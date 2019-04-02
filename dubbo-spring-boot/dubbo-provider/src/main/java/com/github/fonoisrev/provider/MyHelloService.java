package com.github.fonoisrev.provider;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.fonoisrev.api.HelloService;
import org.springframework.stereotype.Component;

@Service(interfaceClass = HelloService.class)
@Component
public class MyHelloService implements HelloService {
    
    @Override
    public String sayHello(String hello) {
        return "Hello world! " + hello;
    }
    
    public static void main(String[] args) {
        //language=JSON
        String json = "{\"additionalFee\":0.00,\"apiVersion\":\"15.0\",\"apnType\":\"1\",\"appPackage\":\"com.coloros.cloud\",\"businessChannelId\":\"00040001\",\"businessType\":\"WANGYOU\",\"channel\":\"paypal\",\"cocoinPartnerOrder\":\"COCOIN20190329055831165308444616\",\"cocoinPayAmount\":80.00,\"cocoinRechargeAmount\":80.00,\"country\":\"ID\",\"currency\":\"USD\",\"id\":53,\"imei\":\"\",\"mobileModel\":\"CPH1871\",\"notifyUrl\":\"https://album-sg01a.ocloud.oppomobile.com/pay/v1/payNotify.json\",\"orderType\":\"RECHARGE_SPEND\",\"partnerId\":\"231810428\",\"partnerOrder\":\"OCLOUD1553839108674K209K439900487K80\",\"partnerParams\":\"\",\"payAmount\":0.8000,\"payRequestId\":\"ID201903290558310439900487464882\",\"payType\":\"paypal\",\"priceLocal\":0.8000,\"productCount\":\"\",\"productDesc\":\"OPPO Cloud\",\"productName\":\"OPPO Cloud\",\"requestTime\":\"2019-03-29 05:58:31\",\"returnUrl\":\"\",\"sdkVersion\":\"12.0\",\"ssoid\":\"439900487\",\"status\":\"SUCCESS\",\"successTime\":\"2019-03-29T05:59:05.027\"}";
      
    }
}
