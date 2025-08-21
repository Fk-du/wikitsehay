package com.bank.tsehay.wikitsehay.controller.user;

import com.bank.tsehay.wikitsehay.dto.user.UserResponse;
import com.bank.tsehay.wikitsehay.mapper.UserMapper;
import com.bank.tsehay.wikitsehay.model.user.User;
import com.bank.tsehay.wikitsehay.service.user.TokenBlacklistService;
import com.bank.tsehay.wikitsehay.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/info")
@RequiredArgsConstructor
public class SelfController {
     private final UserMapper userMapper;

    @GetMapping("/me")
    public UserResponse getMyProfile(Authentication auth) {
        User user = (User) auth.getPrincipal();
        return userMapper.toUserResponse(user);
    }
}
