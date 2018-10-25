package com.github.fonoisrev.stream;


import com.github.fonoisrev.common.WordFrequency;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


/**
 * 读取文件中单词的频率
 */
public class FileReader {
    
    public static WordFrequency read(File file) {
        WordFrequency wf = new WordFrequency();
        try {
            System.out.println("开始读取文件:" + file.getName());
            Files.lines(file.toPath())
                 .filter(line -> line != null && line.length() > 0)
                 .forEach(line -> {
/**
 * 时间关系，没有做到非常完备的单词标点符号分隔
 */
                     String[] words = line.toLowerCase().split("\\s+");
                     for (String word : words) {
                         wf.addWord(word);
                     }
                 });
            System.out.println("成功读完文件:" + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wf;
    }
}
