package com.github.fonoisrev;

import com.zhixiangli.code.similarity.CodeSimilarity;
import com.zhixiangli.code.similarity.strategy.CosineSimilarity;

/**
 * https://github.com/zhixiangli/code-similarity
 */
public class App {
    
    public static void main(String[] args) {
        String a = "public static void main(String[] args) {System.out.println(1);}";
        String b = "public static void main(String[] args) {System.out.println(2);}";

// default algorithm is Longest Common Subsequence.
        CodeSimilarity codeSimilarity = new CodeSimilarity();
        System.out.println(codeSimilarity.get(a, b));

// change similarity algorithm to Cosine Distance.
        CodeSimilarity cosineSimilarity = new CodeSimilarity(new CosineSimilarity());
        System.out.println(cosineSimilarity.get(a, b));
    }
}
