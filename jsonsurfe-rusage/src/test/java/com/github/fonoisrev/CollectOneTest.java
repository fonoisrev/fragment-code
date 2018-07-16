package com.github.fonoisrev;

import org.jsfr.json.*;
import org.junit.Test;

public class CollectOneTest {
    
    String json = "{\"Header\":{\"orderNo\":\"acb0072304987654321\",\n" +
                  "\"activityCode\":\"123456\"},\"Body\":{\"reqSys\":\"0072\"," +
                  "\"homeProv\":\"210\"}}";
    
    String bankId = "$..bankId";
    
    @Test
    public void testJacksonSurfer() {
        JsonSurfer surfer = JsonSurferJackson.INSTANCE;
        surfer.collectOne(json, bankId);
    }
    
    @Test
    public void testGsonSurfer() {
        JsonSurfer surfer = JsonSurferGson.INSTANCE;
        surfer.collectOne(json, bankId);
    }
    
    @Test
    public void testJsonSimpleSurfer() {
        JsonSurfer surfer = JsonSurferJsonSimple.INSTANCE;
        surfer.collectOne(json, bankId);
    }
    
    @Test
    public void testFastjsonSurfer() {
        JsonSurfer surfer = JsonSurferFastJson.INSTANCE;
        surfer.collectOne(json, bankId);
    }
    
}
