package com.bank.tsehay.wikitsehay.controller;


import com.bank.tsehay.wikitsehay.dto.user.LoginRequest;
import com.bank.tsehay.wikitsehay.dto.user.LoginResponse;
import com.bank.tsehay.wikitsehay.dto.user.RegisterUserRequest;
import com.bank.tsehay.wikitsehay.dto.user.RegisterUserResponse;
import com.bank.tsehay.wikitsehay.service.user.AuthService;
import com.bank.tsehay.wikitsehay.service.user.TokenBlacklistService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TokenBlacklistService tokenBlackListService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserRequest request) {
        RegisterUserResponse response = authService.registerUser(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            tokenBlackListService.add(token); // store token in blacklist
        }
        return ResponseEntity.ok("Logged out successfully");
    }

}
