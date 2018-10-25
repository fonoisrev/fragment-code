package com.github.fonoisrev.thread;

import com.github.fonoisrev.common.WordFrequency;

import java.io.*;
import java.util.concurrent.CountDownLatch;

/**
 * 并发读取文件任务
 */
public class CountFileWordsTask implements Runnable {
    
    /**
     * 共享变量，用于统计单词出现次数
     */
    private WordFrequency wordFrequency;
    
    /**
     * 读取的文件
     */
    private File fileToRead;
    
    /**
     * 信号量，读完文件信号量减 1
     */
    private CountDownLatch countDownLatch;
    
    public CountFileWordsTask(File fileToRead, WordFrequency wordFrequency, CountDownLatch latch) {
        this.fileToRead = fileToRead;
        this.wordFrequency = wordFrequency;
        this.countDownLatch = latch;
    }
    
    
    @Override
    public void run() {
        try (BufferedReader r = new BufferedReader(new FileReader(fileToRead))) {
            System.out.println("开始读取文件:" + fileToRead.getName());
            
            String line;
            while ((line = r.readLine()) != null) {
                if (line.length() == 0) {
                    continue;
                }
    
/**
 * 时间关系，没有做到非常完备的单词标点符号分隔
 */
                String[] words = line.toLowerCase().split("\\s+");
                for (String word : words) {
                    wordFrequency.addWord(word);
                }
            }
    
            System.out.println("成功读完文件:" + fileToRead.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        countDownLatch.countDown();
    }
}
