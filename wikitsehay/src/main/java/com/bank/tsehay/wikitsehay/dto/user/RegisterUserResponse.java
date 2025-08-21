package com.bank.tsehay.wikitsehay.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserResponse {
    private Long id;
    private String firstname;
    private String middleName;
    private String lastName;
    private String email;
    private String role;
    private String department;
    private String employeeId;
}