package com.bank.tsehay.wikitsehay.service.user;

import com.bank.tsehay.wikitsehay.dto.user.LoginRequest;
import com.bank.tsehay.wikitsehay.dto.user.LoginResponse;
import com.bank.tsehay.wikitsehay.dto.user.RegisterUserRequest;
import com.bank.tsehay.wikitsehay.dto.user.RegisterUserResponse;
import com.bank.tsehay.wikitsehay.mapper.UserMapper;
import com.bank.tsehay.wikitsehay.model.Department;
import com.bank.tsehay.wikitsehay.model.user.User;
import com.bank.tsehay.wikitsehay.model.user.Role;
import com.bank.tsehay.wikitsehay.repository.DepartmentRepository;
import com.bank.tsehay.wikitsehay.repository.RoleRepository;
import com.bank.tsehay.wikitsehay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService; // Service to generate JWT tokens



    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest request) {
        // Check if email already exists
        if (userRepository.existsByCompanyEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        // Fetch role and department
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        // Create user
        User user = User.builder()
                .name(request.getName())
                .companyEmail(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(role)
                .department(department)
                .build();

        User savedUser = userRepository.save(user);

        // Prepare response
        return userMapper.toRegisterUserResponse(savedUser);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        // Find user by email
        User user = userRepository.findByCompanyEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // Check password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Generate JWT token
        String token = jwtService.generateToken(user.getCompanyEmail());


        LoginResponse response = LoginResponse.builder()
                .token(token)
                .user(userMapper.toUserResponse(user))
                .build();
        return response;
    }
}



