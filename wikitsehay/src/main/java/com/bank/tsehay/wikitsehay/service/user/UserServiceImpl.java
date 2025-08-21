package com.bank.tsehay.wikitsehay.service.user;

import com.bank.tsehay.wikitsehay.dto.user.UpdateUserRequest;
import com.bank.tsehay.wikitsehay.dto.user.UserResponse;
import com.bank.tsehay.wikitsehay.mapper.UserMapper;
import com.bank.tsehay.wikitsehay.model.Department;
import com.bank.tsehay.wikitsehay.model.user.Role;
import com.bank.tsehay.wikitsehay.model.user.User;
import com.bank.tsehay.wikitsehay.repository.DepartmentRepository;
import com.bank.tsehay.wikitsehay.repository.RoleRepository;
import com.bank.tsehay.wikitsehay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return UserResponse.fromEntity(user);
    }

    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request, boolean isAdmin) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(request.getFirstName() != null ? request.getFirstName() : user.getFirstName());
        user.setMiddleName(request.getMiddleName() != null ? request.getMiddleName() : user.getMiddleName());
        user.setLastName(request.getLastName() != null ? request.getLastName() : user.getLastName());
        user.setPhone(request.getPhone() != null ? request.getPhone() : user.getPhone());

        if (isAdmin) {
            user.setTitle(request.getTitle() != null ? request.getTitle() : user.getTitle());

            if (request.getDepartmentId() != null) {
                Department department = departmentRepository.findById(request.getDepartmentId())
                        .orElseThrow(() -> new RuntimeException("Department not found"));
                user.setDepartment(department);
            }

            if (request.getRoleId() != null) {
                Role role = roleRepository.findById(request.getRoleId())
                        .orElseThrow(() -> new RuntimeException("Role not found"));
                user.setRole(role);
            }
        }

        User updatedUser = userRepository.save(user);
        return UserResponse.fromEntity(updatedUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::fromEntity)
                .toList();
    }
}
