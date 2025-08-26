package com.bank.tsehay.wikitsehay.model.user;


import com.bank.tsehay.wikitsehay.model.Department;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;
    private String middleName;
    private String lastName;
    @Column(unique = true, nullable = false)
    private String companyEmail;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String employeeId;

    private String title;

    // --- Relationships ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    // --- Security fields ---
    @Column(nullable = false)
    private String password; // store as BCrypt hash

    private boolean active = true; // account status

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return companyEmail; // or employeeId, depending on your login field
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // or add a field to track expiration
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // or add a field for lock status
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // or add a field for credential expiration
    }

    @Override
    public boolean isEnabled() {
        return active; // map to your entity's "active" field
    }

}
