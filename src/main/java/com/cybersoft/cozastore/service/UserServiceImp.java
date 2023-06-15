package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.payload.request.SignupRequest;

import java.util.List;

public interface UserServiceImp {

    List<UserEntity> getAllUsers();

    boolean addUser(SignupRequest signupRequest);
}
