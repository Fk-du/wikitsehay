package com.bank.tsehay.wikitsehay.controller.user;

import com.bank.tsehay.wikitsehay.dto.user.UpdateUserRequest;
import com.bank.tsehay.wikitsehay.dto.user.UserResponse;
import com.bank.tsehay.wikitsehay.mapper.UserMapper;
import com.bank.tsehay.wikitsehay.model.user.User;
import com.bank.tsehay.wikitsehay.service.user.TokenBlacklistService;
import com.bank.tsehay.wikitsehay.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 🔹 Only authenticated users can see their details
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id, Authentication auth) {
        UserResponse user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    // 🔹 Update user
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request,
            Authentication auth) {

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        UserResponse updatedUser = userService.updateUser(id, request, isAdmin);
        return ResponseEntity.ok(updatedUser);
    }

    // 🔹 Admin can get all users
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
