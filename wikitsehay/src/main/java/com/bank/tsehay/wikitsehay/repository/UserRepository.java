package com.bank.tsehay.wikitsehay.repository;

import com.bank.tsehay.wikitsehay.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"department", "role"})  // tells Hibernate to fetch department eagerly
    Optional<User> findByCompanyEmail(String email);

    @EntityGraph(attributePaths = {"department", "role"})
    Optional<User> findById(Long id);

    @EntityGraph(attributePaths = {"department", "role"})  // tells Hibernate to fetch department eagerly
    boolean existsByCompanyEmail(String email);
    @EntityGraph(attributePaths = {"department", "role"})  // tells Hibernate to fetch department eagerly
    boolean existsByPhone(String email);
    @EntityGraph(attributePaths = {"department", "role"})  // tells Hibernate to fetch department eagerly
    boolean existsByEmployeeId(String email);

    /// //////////////////////////////////////////////////

    @EntityGraph(attributePaths = {"department", "role"})
    Page<User> findByDepartmentId(Long departmentId, Pageable pageable);

    @EntityGraph(attributePaths = {"department", "role"})
    Page<User> findByDepartmentIdAndCompanyEmailContainingIgnoreCase(
            Long departmentId,
            String email,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {"department", "role"})
    Page<User> findByDepartmentIdAndFirstNameContainingIgnoreCase(
            Long departmentId,
            String firstName,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {"department", "role"})
    List<User> findByCompanyEmailContainingIgnoreCaseOrFirstNameContainingIgnoreCase(String email, String firstName);
}