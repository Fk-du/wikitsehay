package com.bank.tsehay.wikitsehay.mapper;

import com.bank.tsehay.wikitsehay.dto.user.RegisterUserResponse;
import com.bank.tsehay.wikitsehay.dto.user.UserResponse;
import com.bank.tsehay.wikitsehay.model.Department;
import com.bank.tsehay.wikitsehay.model.user.Role;
import com.bank.tsehay.wikitsehay.model.user.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-21T10:25:17+0300",
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
        registerUserResponse.role( userRoleName( user ) );
        registerUserResponse.department( userDepartmentName( user ) );
        registerUserResponse.employeeId( user.getEmployeeId() );
        registerUserResponse.id( user.getId() );
        registerUserResponse.middleName( user.getMiddleName() );
        registerUserResponse.lastName( user.getLastName() );

        return registerUserResponse.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.role( userRoleName( user ) );
        userResponse.department( userDepartmentName( user ) );
        userResponse.id( user.getId() );
        userResponse.companyEmail( user.getCompanyEmail() );
        userResponse.firstName( user.getFirstName() );
        userResponse.middleName( user.getMiddleName() );
        userResponse.lastName( user.getLastName() );
        userResponse.phone( user.getPhone() );
        userResponse.title( user.getTitle() );

        return userResponse.build();
    }

    private String userRoleName(User user) {
        if ( user == null ) {
            return null;
        }
        Role role = user.getRole();
        if ( role == null ) {
            return null;
        }
        String name = role.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String userDepartmentName(User user) {
        if ( user == null ) {
            return null;
        }
        Department department = user.getDepartment();
        if ( department == null ) {
            return null;
        }
        String name = department.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
