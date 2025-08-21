package com.bank.tsehay.wikitsehay.service.user;

import com.bank.tsehay.wikitsehay.dto.user.CreateRoleRequest;
import com.bank.tsehay.wikitsehay.dto.user.RoleResponse;
import com.bank.tsehay.wikitsehay.model.user.Role;
import com.bank.tsehay.wikitsehay.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleResponse createRole(CreateRoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setPermissions(request.getPermissions());
        Role saved = roleRepository.save(role);
        return mapToResponse(saved);
    }

    @Override
    public RoleResponse getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
        return mapToResponse(role);
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponse updateRole(Long id, CreateRoleRequest request) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
        role.setName(request.getName());
        role.setPermissions(request.getPermissions());
        Role updated = roleRepository.save(role);
        return mapToResponse(updated);
    }

    @Override
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found with id " + id);
        }
        roleRepository.deleteById(id);
    }

    private RoleResponse mapToResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .permissions(role.getPermissions())
                .build();
    }
}

