package com.xstream.springsecuritydemo.config;

import com.xstream.springsecuritydemo.doamin.Role;
import com.xstream.springsecuritydemo.doamin.User;
import com.xstream.springsecuritydemo.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
@Configuration
public class DataConfig {
    @Bean @Autowired
    CommandLineRunner commandLineRunner(UserService userService){
        return args -> {
            //adding the roles
            userService.saveRole(new Role(null,"ADMIN"));
            userService.saveRole(new Role(null,"USER"));

            //Adding the users
            userService.saveUser(new User(null,"Pavan Samaranayake","psam","123",new ArrayList<>()));
            userService.saveUser(new User(null,"John Doe","jdoe","1234",new ArrayList<>()));
            userService.saveUser(new User(null,"Alex Holder","aholder","1111",new ArrayList<>()));

            //adding roles to the users
            userService.addRoleToUser("psam","ADMIN");
            userService.addRoleToUser("jdoe","USER");


        };
    }
}
