package com.github.fonoisrev.nio;

import java.io.IOException;

public class TimeServer {
    
    public static void main(String[] args) throws IOException {
    
        MultipleExerTimeServer multipleExerTimeServer = new MultipleExerTimeServer(8080);
        new Thread(multipleExerTimeServer).start();
        
        
    }
    
}
