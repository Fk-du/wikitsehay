package com.bank.tsehay.wikitsehay.mapper;


import com.bank.tsehay.wikitsehay.dto.user.RegisterUserResponse;
import com.bank.tsehay.wikitsehay.dto.user.UserResponse;
import com.bank.tsehay.wikitsehay.model.user.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    // Map User entity to UserResponse DTO
    public UserResponse toUserResponse(User user) {
        if (user == null) return null;

        return UserResponse.builder()
                .id(user.getId())
                .companyEmail(user.getCompanyEmail())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .title(user.getTitle())
                .department(user.getDepartment() != null ? user.getDepartment().getName() : null)
                .role(user.getRole() != null ? user.getRole().getName() : null)
                .build();
    }

    // Map User entity to RegisterUserResponse DTO
    public RegisterUserResponse toRegisterUserResponse(User user) {
        if (user == null) return null;

        return RegisterUserResponse.builder()
                .id(user.getId())
                .firstname(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .email(user.getCompanyEmail())
                .employeeId(user.getEmployeeId())
                .role(user.getRole() != null ? user.getRole().getName() : null)
                .department(user.getDepartment() != null ? user.getDepartment().getName() : null)
                .build();
    }

    // Optional: Map List<User> to List<UserResponse>
    public List<UserResponse> toUserResponseList(List<User> users) {
        if (users == null) return List.of();
        return users.stream()
                .map(this::toUserResponse)
                .collect(Collectors.toList());
    }

    // Optional: Map List<User> to List<RegisterUserResponse>
    public List<RegisterUserResponse> toRegisterUserResponseList(List<User> users) {
        if (users == null) return List.of();
        return users.stream()
                .map(this::toRegisterUserResponse)
                .collect(Collectors.toList());
    }
}
