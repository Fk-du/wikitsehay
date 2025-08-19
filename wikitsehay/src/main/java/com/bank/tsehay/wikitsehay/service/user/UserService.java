package com.bank.tsehay.wikitsehay.service.user;

import com.bank.tsehay.wikitsehay.dto.user.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
}
