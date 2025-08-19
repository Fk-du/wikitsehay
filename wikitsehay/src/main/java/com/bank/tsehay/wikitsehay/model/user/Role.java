package com.bank.tsehay.wikitsehay.model.user;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name; // e.g., Admin, Manager, Staff

    @Column(length = 500)
    private String permissions; // e.g., JSON or comma-separated list: "FILES,PROJECTS,RESOURCES"

    // Optional: Users with this role
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<User> users;
}
