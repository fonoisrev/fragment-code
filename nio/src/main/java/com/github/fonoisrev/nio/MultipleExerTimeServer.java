package com.github.fonoisrev.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultipleExerTimeServer implements Runnable {
    
    private boolean stop;
    
    private Selector selector;
    
    private ServerSocketChannel ssc;
    
    
    public MultipleExerTimeServer(int port) {
        try {
            selector = Selector.open();
            ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            
            ssc.socket().bind(new InetSocketAddress(port));
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server is start in port: " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (IOException e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            
        }
    }
    
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                // Accept
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel accept = ssc.accept();
                accept.configureBlocking(false);
                accept.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "utf-8");
                    System.out.println("The time server receive order : " + body);
                    String result = null;
                    if ("QUERY TIME ORDER".equalsIgnoreCase(body)) {
                        result = new Date().toString();
                    } else {
                        result = "BAD ORDER!";
                    }
                    doWrite(sc, result);
                } else if (readBytes < 0) {
                    key.cancel();
                    sc.close();
                } else {
                
                }
            }
        }
    }
    
    private void doWrite(SocketChannel sc, String result) throws IOException {
        ByteBuffer writeBuffer = ByteBuffer.wrap(result.getBytes());
        sc.write(writeBuffer);
    }
    
    public void stop() {
        stop = true;
    }
}
