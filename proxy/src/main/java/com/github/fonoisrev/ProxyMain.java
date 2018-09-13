package com.github.fonoisrev;

import com.github.fonoisrev.proxy.JdkDynamicProxy;
import com.github.fonoisrev.set.UserService;
import com.github.fonoisrev.set.UserServiceImpl;

import java.lang.reflect.Proxy;

public class ProxyMain {
    
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
    
        JdkDynamicProxy proxy = new JdkDynamicProxy(service);
    
        Object p = Proxy.newProxyInstance(ProxyMain.class.getClassLoader(),
                                          service.getClass().getInterfaces(), proxy);
        ((UserService) p).add();
    }
}
