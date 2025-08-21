package com.bank.tsehay.wikitsehay.service.user;

import com.bank.tsehay.wikitsehay.dto.user.UpdateUserRequest;
import com.bank.tsehay.wikitsehay.dto.user.UserResponse;
import com.bank.tsehay.wikitsehay.model.user.User;

import java.util.List;

public interface UserService {
    UserResponse getUser(Long id);
    UserResponse updateUser(Long id, UpdateUserRequest request, boolean isAdmin);
    List<UserResponse> getAllUsers();
}
