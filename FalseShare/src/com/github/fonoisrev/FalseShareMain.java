package com.github.fonoisrev;

import sun.misc.Contended;

public class FalseShareMain {
    
    // 线程数越大争用越多
    static int NUM_THREADS = 4;
    
    static long ITERATIONS = 50 * 1000L * 1000L;
    
    // 重复次数
    static int REPEAT = 2;
    
    static VolatileLong[] longs;
    
    static {
        longs = new VolatileLong[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            longs[i] = new VolatileLong();
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        long sumTime = 0l;
        for (int j = 0; j < REPEAT; j++) {
            long start = System.currentTimeMillis();
            runTest();
            sumTime += System.currentTimeMillis() - start;
        }
        System.out.println("平均耗时" + (sumTime/REPEAT));
    }
    
    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new RunThread(i);
        }
        
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }
    
    static class RunThread extends Thread {
        
        int index;
        
        public RunThread(int index) {
            this.index = index;
        }
        
        @Override
        public void run() {
            long i = ITERATIONS + 1;
            while (0 != --i)
            {
                longs[index].value = i;
            }
        }
    }
}

// 增充对象有两种方式，第一种使用@Contened注解，此方法只有在在Java8中可用
@Contended
// 注意要配合虚拟机参数     -XX:-RestrictContended 使用
class VolatileLong {
    
    public volatile long value = 0L;
    
    /**
     * 增充对象有两种方式，第二种使用变量增充
     * Java 6以后的版本，无用的变量可能会被删除
     * 但在此处用IDEA+JDK8测试变量增充仍旧有效
     */
    
    // 64bit 的虚拟机对象头是16字节，因此需要5个long对象填充
//    public long p1, p2, p3, p4, p5;
    
    // 32位的虚拟机对象头是8字节，因此需要6个对象
//    public long p6;
}



