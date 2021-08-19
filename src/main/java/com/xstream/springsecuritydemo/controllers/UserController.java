package com.xstream.springsecuritydemo.controllers;

import com.xstream.springsecuritydemo.doamin.Role;
import com.xstream.springsecuritydemo.doamin.RoleUserDTO;
import com.xstream.springsecuritydemo.doamin.User;
import com.xstream.springsecuritydemo.services.interfaces.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/security")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {

        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {

        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }


    @PostMapping("/roles")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/security/roles").toUriString());
        return new ResponseEntity<>(userService.saveRole(role), HttpStatus.CREATED);
    }

    @PostMapping("users/roles")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleUserDTO dtoObj) {
        userService.addRoleToUser(dtoObj.getUsername(), dtoObj.getRoleName());
        return new ResponseEntity<>("Added Roles to the user " + dtoObj.getUsername(), HttpStatus.OK);
    }

}


