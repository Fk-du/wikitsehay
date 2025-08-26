package com.bank.tsehay.wikitsehay.service.user;

import com.bank.tsehay.wikitsehay.dto.user.ChangePasswordRequest;
import com.bank.tsehay.wikitsehay.dto.user.UpdateUserRequest;
import com.bank.tsehay.wikitsehay.dto.user.UserResponse;
import com.bank.tsehay.wikitsehay.model.user.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    UserResponse getUser(Long id);
    UserResponse updateUser(Long id, UpdateUserRequest request, boolean isAdmin);

    Page<UserResponse> getUsersByDepartment(Long departmentId, String filter, int page, int size);

    List<UserResponse> getAllUsers();

    UserResponse updateSelf(Long id, UpdateUserRequest request);

    void changePassword(Long userId, ChangePasswordRequest request);
}
