package com.github.fonoisrev.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 记录字符串在文件中出现的频率，形式如下
 * <pre>
 *     key:单词    value：次数
 *     word1    -> 5
 *     word2    -> 2
 *     word3    -> 4
 * </pre>
 * 这个类是线程安全的
 */
public class WordFrequency {
    
    private final Map<String, Integer> frequencyMap = new ConcurrentHashMap<>(10000);
    
    public void addWord(String word) {
        addWord(word, 1);
    }
    
    public void addWord(String word, int count) {
        frequencyMap.merge(word, count, (num1, num2) -> (num1 + num2));
    }
    
    public WordFrequency addWordFrequency(WordFrequency wordFrequency) {
        wordFrequency.frequencyMap.forEach((word, count) -> addWord(word, count));
        return this;
    }
    
    public Map<String, Integer> getFrequencyMap() {
        return frequencyMap;
    }
}
