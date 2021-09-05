package com.vegga.chitchat.dao;

import java.util.HashSet;
import java.util.Set;

public class UserDaoImpl {

  private static UserDaoImpl instance;
  private Set<String> users;

  private UserDaoImpl() {
    users = new HashSet<>();
  }

  public static synchronized UserDaoImpl getInstance() {
    if (instance == null) {
      instance = new UserDaoImpl();
    }
    return instance;
  }

  public Set<String> getUsers() {
    return users;
  }

  public void setUser(String userName) throws Exception {
    if (users.contains(userName)) {
      throw new Exception("User already exists with userName: " + userName);
    }
    users.add(userName);
  }
}
