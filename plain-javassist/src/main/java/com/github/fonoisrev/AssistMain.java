package com.github.fonoisrev;

import com.github.fonoisrev.bean.Person;
import javassist.*;

import java.io.IOException;

public class AssistMain {
    
    public static void main(String[] args)
            throws NotFoundException, CannotCompileException, IOException {
        // 这个会触发类加载，产生异常
        // java.lang.LinkageError: loader (instance of  sun/misc/Launcher$AppClassLoader): attempted  duplicate class ...
        // new Person();
        
        ClassPool pool = ClassPool.getDefault();
        String name = "com.github.fonoisrev.bean.Person";
        CtClass personClass = pool.get(name);
        CtMethod printNum = personClass.getDeclaredMethod("printNum");
        printNum.setBody("System.out.println(2);");
//        oldPrintNum.setName("printNum1");
        personClass.toClass();
        
        new Person().printNum();
    }
}
