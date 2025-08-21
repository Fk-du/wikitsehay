package com.bank.tsehay.wikitsehay.dto.user;


import com.bank.tsehay.wikitsehay.model.Department;
import com.bank.tsehay.wikitsehay.model.user.Role;
import lombok.Data;

@Data
public class UpdateUserRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private String phone;

    // only ADMIN can change these
    private String title;
    private Long departmentId; // send department id
    private Long roleId;       // send role id
}

