package com.asavin.pilsnerbar.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "usr")
public class User implements UserDetails {
    Long id;
    String name;
    boolean isActive;
    boolean isAdult;
    Long pocket;
    String password;
    boolean isAdmin;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public User(String name, boolean isActive, boolean isAdult, Long pocket, String password) {
        this.name = name;
        this.isActive = isActive;
        this.isAdult = isAdult;
        this.pocket = pocket;
        this.password = password;
    }

    public User(Long id) {
        this.id = id;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(isAdmin?"admin":"user");
    }

    public String getPassword() {
        return password;
    }

    @Override
    @Transient
    public String getUsername() {
        return name;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return isActive;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public Long getPocket() {
        return pocket;
    }

    public void setPocket(Long pocket) {
        this.pocket = pocket;
    }
}
