package com.bank.tsehay.wikitsehay.repository;

import com.bank.tsehay.wikitsehay.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCompanyEmail(String email);
    boolean existsByCompanyEmail(String email);
}