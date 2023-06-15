package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.payload.request.SignupRequest;
import com.cybersoft.cozastore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceImp {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> list = userRepository.findAll();
        return list;
    }

    @Override
    public boolean addUser(SignupRequest request) {
        boolean isSuccess = false;
        try {
            UserEntity newUser = new UserEntity();
            newUser.setUsername(request.getUsername());
            newUser.setEmail(request.getEmail());
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            userRepository.save(newUser);
            isSuccess = true;
        }
        catch (Exception e) {

        }
        return isSuccess;
    }
}
