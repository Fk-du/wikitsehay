package com.bank.tsehay.wikitsehay.repository;

import com.bank.tsehay.wikitsehay.model.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
