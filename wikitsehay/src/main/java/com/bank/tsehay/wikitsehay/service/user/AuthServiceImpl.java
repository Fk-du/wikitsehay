package com.bank.tsehay.wikitsehay.service.user;

import com.bank.tsehay.wikitsehay.dto.user.LoginRequest;
import com.bank.tsehay.wikitsehay.dto.user.LoginResponse;
import com.bank.tsehay.wikitsehay.dto.user.RegisterUserRequest;
import com.bank.tsehay.wikitsehay.dto.user.RegisterUserResponse;
import com.bank.tsehay.wikitsehay.mapper.UserMapper;
import com.bank.tsehay.wikitsehay.model.Department;
import com.bank.tsehay.wikitsehay.model.user.User;
import com.bank.tsehay.wikitsehay.model.user.Role;
import com.bank.tsehay.wikitsehay.model.util.PasswordResetToken;
import com.bank.tsehay.wikitsehay.repository.DepartmentRepository;
import com.bank.tsehay.wikitsehay.repository.RoleRepository;
import com.bank.tsehay.wikitsehay.repository.UserRepository;
import com.bank.tsehay.wikitsehay.repository.util.PasswordResetTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService; // Service to generate JWT tokens

    private final JavaMailSender mailSender; // for sending reset link
    private final PasswordResetTokenRepository tokenRepository;



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
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .lastName(request.getLastName())
                .companyEmail(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(role)
                .department(department)
                .employeeId(request.getEmployeeId())
                .build();

        User savedUser = userRepository.save(user);

        // Prepare response
        return userMapper.toRegisterUserResponse(savedUser);
    }

    // fetch logged in user from SecurityContext
    @Override
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByCompanyEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));
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
//        String token = jwtService.generateToken(user.getCompanyEmail());
        String token = jwtService.generateToken(user);


        LoginResponse response = LoginResponse.builder()
                .token(token)
                .user(userMapper.toUserResponse(user))
                .build();
        return response;
    }

    @Override
    public void forgotPassword(String email) {
        User user = userRepository.findByCompanyEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(1));
        tokenRepository.save(resetToken);

        String resetLink = "http://localhost:8080/api/auth/reset-password?token=" + token;
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Password Reset Request");
        mailMessage.setText("Click the link to reset your password: " + resetLink);
        mailSender.send(mailMessage);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        User user = resetToken.getUser(); // User, not UserDetails
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        tokenRepository.delete(resetToken);
    }
}



