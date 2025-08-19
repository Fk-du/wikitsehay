package com.bank.tsehay.wikitsehay.mapper;

import com.bank.tsehay.wikitsehay.dto.user.RegisterUserResponse;
import com.bank.tsehay.wikitsehay.dto.user.UserResponse;
import com.bank.tsehay.wikitsehay.model.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-19T16:54:17+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public RegisterUserResponse toRegisterUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        RegisterUserResponse.RegisterUserResponseBuilder registerUserResponse = RegisterUserResponse.builder();

        registerUserResponse.email( user.getCompanyEmail() );
        registerUserResponse.id( user.getId() );
        registerUserResponse.name( user.getName() );

        registerUserResponse.role( user.getRole().getName() );
        registerUserResponse.department( user.getDepartment().getName() );

        return registerUserResponse.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.name( user.getName() );
        userResponse.phone( user.getPhone() );

        userResponse.role( user.getRole().getName() );
        userResponse.department( user.getDepartment().getName() );

        return userResponse.build();
    }
}
