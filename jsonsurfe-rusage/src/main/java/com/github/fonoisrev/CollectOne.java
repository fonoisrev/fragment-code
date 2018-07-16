package com.github.fonoisrev;

import org.jsfr.json.JsonSurfer;
import org.jsfr.json.JsonSurferJackson;

public class CollectOne {
    
    public static void main(String[] args) {
        JsonSurfer surfer = JsonSurferJackson.INSTANCE;
    
        //language=JSON
        String json = "{\"Header\":{\"orderNo\":\"acb0072304987654321\",\n" +
                      "\"activityCode\":\"123456\"},\"Body\":{\"reqSys\":\"0072\"," +
                      "\"homeProv\":\"210\"}}";
    
        String bankId = (String) surfer.collectOne(json, "$..bankId");
        System.out.println(bankId);
    }
}
