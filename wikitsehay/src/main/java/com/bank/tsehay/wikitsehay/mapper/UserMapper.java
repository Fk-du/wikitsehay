package com.bank.tsehay.wikitsehay.mapper;

import com.bank.tsehay.wikitsehay.dto.user.RegisterUserResponse;
import com.bank.tsehay.wikitsehay.dto.user.UserResponse;
import com.bank.tsehay.wikitsehay.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "companyEmail", target = "email")
    @Mapping(target = "role", expression = "java(user.getRole().getName())")
    @Mapping(target = "department", expression = "java(user.getDepartment().getName())")
    RegisterUserResponse toRegisterUserResponse(User user);

    @Mapping(target = "role", expression = "java(user.getRole().getName())")
    @Mapping(target = "department", expression = "java(user.getDepartment().getName())")
    UserResponse toUserResponse(User user);
}
