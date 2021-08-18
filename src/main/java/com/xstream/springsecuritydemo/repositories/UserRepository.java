package com.xstream.springsecuritydemo.repositories;

import com.xstream.springsecuritydemo.doamin.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
}
