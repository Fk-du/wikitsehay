package com.bank.tsehay.wikitsehay.controller.user;

import com.bank.tsehay.wikitsehay.dto.user.ChangePasswordRequest;
import com.bank.tsehay.wikitsehay.dto.user.UpdateUserRequest;
import com.bank.tsehay.wikitsehay.dto.user.UserResponse;
import com.bank.tsehay.wikitsehay.mapper.UserMapper;
import com.bank.tsehay.wikitsehay.model.user.User;
import com.bank.tsehay.wikitsehay.repository.UserRepository;
import com.bank.tsehay.wikitsehay.service.user.TokenBlacklistService;
import com.bank.tsehay.wikitsehay.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/user/info")
@RequiredArgsConstructor
public class SelfController {
     private final UserMapper userMapper;
     private final UserRepository userRepository;
     private final UserService userService;

    @GetMapping("/me")
    public UserResponse getMyProfile(Authentication auth) {
        User user = (User) auth.getPrincipal();
        return userMapper.toUserResponse(user);
    }

    @PutMapping("/self")
    public ResponseEntity<UserResponse> updateSelf(@RequestBody UpdateUserRequest request, Principal principal) {
        // Assuming username = email
        User currentUser = userRepository.findByCompanyEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserResponse response = userService.updateSelf(currentUser.getId(), request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @RequestBody ChangePasswordRequest request,
            @AuthenticationPrincipal User user) {

        userService.changePassword(user.getId(), request);
        return ResponseEntity.ok("Password changed successfully");
    }

}
