package com.github.fonoisrev.thread;

import com.github.fonoisrev.common.Constants;
import com.github.fonoisrev.common.WordFrequency;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用 Runnable + 线程池 + CountDownLatch 实现
 * 传统的实现方式
 */
public class Main {
    
    public static void main(String[] args) throws InterruptedException {
        
        ExecutorService threadPool = Executors.newFixedThreadPool(Constants.CONCURRENCE);
        
        File dir = Paths.get(Constants.TARGET_DIR).toFile();
        Constants.checkFileAsDirectory(dir);
        
        File[] files = dir.listFiles();
        // 这里的WordFrequency是线程间共享的变量
        WordFrequency wordFrequency = new WordFrequency();
        CountDownLatch latch = new CountDownLatch(files.length);
        for (File file : files) {
            CountFileWordsTask task =
                    new CountFileWordsTask(file, wordFrequency, latch);
            threadPool.submit(task);
        }
        
        latch.await();
        
        // 统计完成，排序并打印前100个
        List<Map.Entry<String, Integer>> entryList =
                new ArrayList<>(wordFrequency.getFrequencyMap().entrySet());
        Collections.sort(entryList, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(
                    Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        
        System.out.println("出现频率在前100的单词为：");
        for (int i = entryList.size() - 1; i >= 0; i--) {
            Entry<String, Integer> entry = entryList.get(i);
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
