package com.upiiz.bas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 120)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    public Integer getId() { return id; }
    public User setId(Integer id) { this.id = id; return this; }
    public String getFullName() { return fullName; }
    public User setFullName(String fullName) { this.fullName = fullName; return this; }
    public String getEmail() { return email; }
    public User setEmail(String email) { this.email = email; return this; }
    @Override
    public String getPassword() { return password; }
    public User setPassword(String password) { this.password = password; return this; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return List.of(); }
    @Override
    public String getUsername() { return email; }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}
