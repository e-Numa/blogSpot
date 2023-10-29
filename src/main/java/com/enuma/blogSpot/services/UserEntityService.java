package com.enuma.blogSpot.services;

import com.enuma.blogSpot.model.UserEntity;
import com.enuma.blogSpot.payload.request.UserRequest;
import com.enuma.blogSpot.payload.response.UserResponse;

import java.util.List;

public interface UserEntityService {

    UserResponse createUser(UserRequest request);
    UserResponse updateUser(String email, Long id);
    List<UserEntity> getUsers();
    UserResponse getUserByID(Long id);
    UserResponse deleteUser(Long id);
}
