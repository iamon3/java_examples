package com.freeorg.java6.annotations.eg2authentication;

public class UserProfileImpl implements UserProfile {
  @Override
  public void getAddress(String userName, String password) {
    System.out.println("In the implementation. username : " + userName + ", password : " + password);
  }
}
