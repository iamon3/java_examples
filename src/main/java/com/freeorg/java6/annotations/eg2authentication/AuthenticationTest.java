package com.freeorg.java6.annotations.eg2authentication;
import java.lang.reflect.InvocationHandler;
//import org.junit.Test;

public class AuthenticationTest {
  
  public static void main(String[] args){
    // Given
    String userName = "admin";
    String password = "4321";
    InvocationHandler authenticationInvocationHandler  = new AuthenticationInvocationHandler();
    Scannable userProfileProxy = (Scannable) ProxyFactory.getInstance(UserProfileImpl.class, authenticationInvocationHandler);
    
    // When
    ((UserProfile)userProfileProxy).getAddress(userName, password);
  }
  
//  @Test
//  public void authenticateValidUser(){
//    // Given
//    String userName = "admin";
//    String password = "4321";
//    InvocationHandler authenticationInvocationHandler  = new AuthenticationInvocationHandler();
//    Scannable userProfileProxy = (Scannable) ProxyFactory.getInstance(UserProfileImpl.class, authenticationInvocationHandler);
//    
//    // When
//    ((UserProfile)userProfileProxy).getAddress(userName, password);
//  }
}
