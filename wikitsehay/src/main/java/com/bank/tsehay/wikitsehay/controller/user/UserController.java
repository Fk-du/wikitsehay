package com.bank.tsehay.wikitsehay.controller.user;

import com.bank.tsehay.wikitsehay.dto.user.UpdateUserRequest;
import com.bank.tsehay.wikitsehay.dto.user.UserResponse;
import com.bank.tsehay.wikitsehay.mapper.UserMapper;
import com.bank.tsehay.wikitsehay.model.user.User;
import com.bank.tsehay.wikitsehay.repository.UserRepository;
import com.bank.tsehay.wikitsehay.service.user.TokenBlacklistService;
import com.bank.tsehay.wikitsehay.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

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

    @GetMapping("/users")
    public ResponseEntity<Page<UserResponse>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String filter,
            Principal principal
    ) {
        // get logged-in user
        User currentUser = userRepository.findByCompanyEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Long departmentId = currentUser.getDepartment().getId();

        Page<UserResponse> users = userService.getUsersByDepartment(departmentId, filter, page, size);
        return ResponseEntity.ok(users);
    }


    // 🔹 Admin can get all users
//    @GetMapping("/all")
//    public ResponseEntity<List<UserResponse>> getAllUsers() {
//        return ResponseEntity.ok(userService.getAllUsers());
//    }
}
