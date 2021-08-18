package com.xstream.springsecuritydemo.services;

import com.xstream.springsecuritydemo.doamin.Role;
import com.xstream.springsecuritydemo.doamin.User;
import com.xstream.springsecuritydemo.exceptions.NoItemFoundException;
import com.xstream.springsecuritydemo.exceptions.RoleServiceException;
import com.xstream.springsecuritydemo.exceptions.UserServiceException;
import com.xstream.springsecuritydemo.repositories.RoleRepository;
import com.xstream.springsecuritydemo.repositories.UserRepository;
import com.xstream.springsecuritydemo.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.InvalidParameterException;
import java.util.List;
@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        try {
            userRepository.save(user);
        }catch (IllegalStateException e){
            log.info("Error occurred when saving user");
            throw new UserServiceException();
        }
        log.info("Saving user "+user.getName());

        return user;
    }

    @Override
    public Role saveRole(Role role) {
        try {
            roleRepository.save(role);
        }catch (IllegalStateException e){
            log.info("Error occurred when saving role");
            throw new RoleServiceException();
        }
        log.info("Saving role "+role.getName());
        return role;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
            try{
                User user=userRepository.findUserByUsername(username);
                Role role=roleRepository.findRoleByName(roleName);
                user.getRoles().add(role);
                log.info("adding Role"+role.getName()+"Saving user "+user.getName());

            }catch (Exception e){
                throw  new UserServiceException();
            }

    }

    @Override
    public User getUser(String username) {
        if(username.isEmpty() || username.length()==0){
            throw  new InvalidParameterException();
        }
        User user;
        try{
            user=userRepository.findUserByUsername(username);
        }catch (IllegalStateException e) {
            throw new UserServiceException();
        }catch (NullPointerException e){
            throw new NoItemFoundException();
        }
        log.info("retrieving the user"+user.getName());

        return user;
    }

    @Override
    public List<User> getUsers() {

        List<User> usersList;
        try{
            usersList=userRepository.findAll();
        }catch (IllegalStateException e) {
            throw new UserServiceException();
        }catch (NullPointerException e){
            throw new NoItemFoundException();
        }
        log.info("retrieving all the users");

        return usersList;

    }
}
