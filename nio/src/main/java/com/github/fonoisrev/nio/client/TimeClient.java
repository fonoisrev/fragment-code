package com.github.fonoisrev.nio.client;

public class TimeClient {
    
    public static void main(String[] args) {
        TimeClientHandle timeClientHandle = new TimeClientHandle("127.0.0.1", 8080);
        new Thread(timeClientHandle).start();
    }
}
