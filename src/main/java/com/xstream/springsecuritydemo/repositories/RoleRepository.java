package com.xstream.springsecuritydemo.repositories;

import com.xstream.springsecuritydemo.doamin.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);
}
