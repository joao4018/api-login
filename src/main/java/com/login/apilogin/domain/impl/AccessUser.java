package com.login.apilogin.domain.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity(name = "API_ACCESS")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccessUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The user name cannot be empty")
    @NotNull
    @Column(unique = true)
    private String userName;

    @NotEmpty(message = "The user email cannot be empty")
    @NotNull
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "The password cannot be empty")
    @NotNull
    @ToString.Exclude
    private String password;

    @CreationTimestamp
    @NotNull
    private LocalDateTime singUpDate;

    @CreationTimestamp
    private LocalDateTime lastLogin;

    @CreationTimestamp
    private LocalDateTime currentLogin;

    @CreationTimestamp
    @NotNull
    private LocalDateTime premiumValidate;

    private LocalDateTime lastPayament;

    @CreationTimestamp
    @NotNull
    private LocalDateTime accountValidate;


    @NotEmpty(message = "The role cannot be empty")
    @NotNull
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(role.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountValidate.isAfter(LocalDateTime.now());
    }

    @Override
    public boolean isAccountNonLocked() {
        if (this.lastPayament == null){
            return false;
        }
        return this.lastPayament.isBefore(LocalDateTime.now());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.premiumValidate.isAfter(LocalDateTime.now());
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

