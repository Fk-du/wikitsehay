package com.bank.tsehay.wikitsehay.service.user;

import com.bank.tsehay.wikitsehay.dto.user.LoginRequest;
import com.bank.tsehay.wikitsehay.dto.user.LoginResponse;
import com.bank.tsehay.wikitsehay.dto.user.RegisterUserRequest;
import com.bank.tsehay.wikitsehay.dto.user.RegisterUserResponse;

public interface AuthService {
    RegisterUserResponse registerUser(RegisterUserRequest request);

    LoginResponse login(LoginRequest request);
}
