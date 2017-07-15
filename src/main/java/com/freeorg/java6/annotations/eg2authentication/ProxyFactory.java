package com.freeorg.java6.annotations.eg2authentication;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {
  public static Object getInstance(Class<?> clazz, InvocationHandler invocationHandler) {    
    return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), invocationHandler);
  }
}
