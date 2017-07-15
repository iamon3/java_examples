package com.freeorg.java6.annotations.eg2authentication;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationInvocationHandler implements InvocationHandler {
 
  private Map<Class, Object> classInstanceMap = new HashMap<>();
  
  {
    classInstanceMap.put(UserProfile.class, new UserProfileImpl());
  }
  
  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("Method Name : " + method.getName() + ", args : " + Arrays.toString(args));
    if (method.isAnnotationPresent(BasicAuth.class)){
      String userName = (String) args[0];
      String password = (String) args[1];
      System.out.println("Intercepted method call. Authnticating user with username : " + userName + ", password : " + password);
    }
    Object result = method.invoke((classInstanceMap.get(method.getDeclaringClass())), args);
    return result;
  }
}
