import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws IOException {
        String expStackOverflow = "\\{(.|\\n)*?\\}";
        String expSuccess = "\\{[\\w\\W]*\\}";
        
        //language=JSON
        String json = "{\"activityCode\":\"U101_001\",\"reqSys\":\"0069\"," +
                      "\"reqDate\":\"20180830\",\"reqDateTime\":\"20180830102823\"," +
                      "\"upayDate\":\"20180830\",\"upayDateTime\":\"20180830102904\"," +
                      "\"upayTransId\":\"10201808301028370561825684311492\"," +
                      "\"orderNo\":\"00692018083010282323516087179888\"," +
                      "\"payTrans\":\"00692018083010282323516087179888\",\"version\":null," +
                      "\"buyerId\":\"buyerID\",\"orderMoney\":1,\"payment\":1,\"gift\":0," +
                      "\"merActivityId\":\"\",\"paymentType\":\"WEIXIN-JSAPI\"," +
                      "\"paymentLimit\":\"\",\"productId\":\"123456\",\"productName\":\"充值\"," +
                      "\"productDesc\":\"在线支付充值\",\"productUrl\":\"http://127.0.0" +
                      ".1:8086/ProductShow\",\"notifyUrl\":\"http://127.0.0" +
                      ".1:8086/payprod-sinmulator/merchantPayNotify.action\"," +
                      "\"returnUrl\":\"http://127.0.0.1:8086\",\"clientIp\":\"127.0.0.1\"," +
                      "\"customParam\":\"key=value|key2=value2\",\"weixinAppId\":\"weixinAppId\"," +
                      "\"weixinOpenId\":\"weixinOpenId\",\"resultCode\":null,\"resultDesc\":null," +
                      "\"settleDate\":null,\"payUrl\":null,\"parameters\":null,\"rspCode\":null," +
                      "\"rspDesc\":null,\"notifyDate\":null,\"notifyDateTime\":null," +
                      "\"idType\":\"01\",\"idValue\":\"13510434519\",\"homeProv\":null," +
                      "\"reqTransId\":\"00447361103087895057951293738685\"," +
                      "\"payOrgTransId\":null,\"systemId\":\"10201808301017578896538704424701\"," +
                      "\"reSendCount\":0,\"defaultBank\":\"\",\"reSendDelayTimes\":null," +
                      "\"account\":null,\"productType\":\"VIRTUAL\",\"busiType\":\"\"," +
                      "\"authCode\":\"32888691083801188402\",\"reqChannel\":\"\"," +
                      "\"areaBusinessHallCode\":\"\",\"businessHallCode\":\"\"," +
                      "\"businessHallWindowCode\":\"\",\"terminalCode\":\"\",\"clerkCode\":\"\"," +
                      "\"contractCode\":null,\"shRule\":null,\"feeFlag\":null," +
                      "\"couponsInvFlag\":null,\"signInfoBean\":null,\"timeoutExpress\":\"60m\"," +
                      "\"merId\":null,\"extraParams\":null,\"payDateTime\":null," +
                      "\"clientType\":null,\"onlyHeader\":null}";
        
        json.matches(expSuccess);
        json.matches(expStackOverflow);
    
    }
}
