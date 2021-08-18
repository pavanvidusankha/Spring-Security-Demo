package com.xstream.springsecuritydemo.services.interfaces;

import com.xstream.springsecuritydemo.doamin.Role;
import com.xstream.springsecuritydemo.doamin.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User> getUsers();
}
