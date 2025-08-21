package com.bank.tsehay.wikitsehay.service.user;

import com.bank.tsehay.wikitsehay.dto.user.CreateRoleRequest;
import com.bank.tsehay.wikitsehay.dto.user.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(CreateRoleRequest request);
    RoleResponse getRoleById(Long id);
    List<RoleResponse> getAllRoles();
    RoleResponse updateRole(Long id, CreateRoleRequest request);
    void deleteRole(Long id);
}

