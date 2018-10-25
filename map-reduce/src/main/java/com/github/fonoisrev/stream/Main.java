package com.github.fonoisrev.stream;


import com.github.fonoisrev.common.Constants;
import com.github.fonoisrev.common.WordFrequency;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 基于Java 8 流的语法实现
 *
 * 使用M内置的ForkJoinPool-common线程池
 */
public class Main {
    
    
    public static void main(String[] args)
            throws IOException, ExecutionException, InterruptedException {
        
        // ForkJoinPool并发度设为5
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
                           String.valueOf(Constants.CONCURRENCE));
        
        File dir = Paths.get(Constants.TARGET_DIR).toFile();
        Constants.checkFileAsDirectory(dir);
        
        File[] files = dir.listFiles();
        // 启动并行流读文件并统计
        WordFrequency wordFrequency =
                Arrays.asList(files)
                      .parallelStream()
                      .map(FileReader::read)
                      .reduce(WordFrequency::addWordFrequency)
                      .get();
        
        System.out.println("出现频率在前100的单词为：");
        wordFrequency.getFrequencyMap()
                     .entrySet()
                     .stream()
                     .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                     .limit(100)
                     .forEachOrdered(entry -> System.out
                             .println(entry.getKey() + " : " + entry.getValue()));
        
    }
    
    
}
