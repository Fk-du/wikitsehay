package com.bank.tsehay.wikitsehay.mapper;

import com.bank.tsehay.wikitsehay.dto.user.RegisterUserResponse;
import com.bank.tsehay.wikitsehay.dto.user.UserResponse;
import com.bank.tsehay.wikitsehay.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "companyEmail", target = "email")
    @Mapping(source = "role.name", target = "role")
    @Mapping(source = "department.name", target = "department")
    @Mapping(source = "employeeId", target = "employeeId")
    RegisterUserResponse toRegisterUserResponse(User user);


    @Mapping(source = "role.name", target = "role")
    @Mapping(source = "department.name", target = "department")
    UserResponse toUserResponse(User user);
}
