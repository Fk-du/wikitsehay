package com.bank.tsehay.wikitsehay.dto.user;

import com.bank.tsehay.wikitsehay.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String companyEmail;
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;
    private String title;
    private Long department;
    private String role;

    // mapper method
    public static UserResponse fromEntity(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .companyEmail(user.getCompanyEmail())
                .firstName(user.getFirstName())
                .middleName(user.getMiddleName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .title(user.getTitle())
                .department(user.getDepartment().getId())
                .role(user.getRole() != null ? user.getRole().getName() : null)
                .build();
    }
}
