package com.bank.tsehay.wikitsehay.service.user;

import com.bank.tsehay.wikitsehay.dto.user.LoginRequest;
import com.bank.tsehay.wikitsehay.dto.user.LoginResponse;
import com.bank.tsehay.wikitsehay.dto.user.RegisterUserRequest;
import com.bank.tsehay.wikitsehay.dto.user.RegisterUserResponse;
import com.bank.tsehay.wikitsehay.model.user.User;

public interface AuthService {
    RegisterUserResponse registerUser(RegisterUserRequest request);
    // fetch logged in user from SecurityContext
    User getCurrentUser();
    LoginResponse login(LoginRequest request);
    void forgotPassword(String email);
    void resetPassword(String token, String newPassword);
}
