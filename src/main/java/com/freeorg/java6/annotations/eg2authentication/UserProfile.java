package com.freeorg.java6.annotations.eg2authentication;
public interface UserProfile extends Scannable{
  @BasicAuth
  public void getAddress(String userName, String password);
}
