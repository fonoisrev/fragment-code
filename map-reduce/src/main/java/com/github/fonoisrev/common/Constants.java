package com.github.fonoisrev.common;

import java.io.File;

public class Constants {
    
    /**
     * 指向存放100个文件的目录，假定无子目录
     * 请修改目录
     */
    public static final String TARGET_DIR = "D:\\test1";
    
    /**
     * 并发线程数，在4核cpu上实测，设为5会卡电脑
     */
    public static final int CONCURRENCE = 5;
    
    public static void checkFileAsDirectory(File target) {
        if (!target.exists()) {
            throw new RuntimeException(Constants.TARGET_DIR + "不存在");
        }
        if (!target.isDirectory()) {
            throw new RuntimeException(Constants.TARGET_DIR + "不是目录");
        }
    }
}
