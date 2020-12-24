package com.asavin.pilsnerbar.entity;

import com.asavin.pilsnerbar.json.UserView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

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



    List<Order> orders;
    public User(String name, boolean isActive, boolean isAdult, Long pocket, String password, boolean isAdmin) {
        this.name = name;
        this.isActive = isActive;
        this.isAdult = isAdult;
        this.pocket = pocket;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    @JsonView(UserView.UserFullInfoView.class)
    @OneToMany(mappedBy = "user")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @JsonIgnore
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
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(isAdmin?"admin":"user");
    }
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    @Transient
    @JsonIgnore
    public String getUsername() {
        return name;
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    @Transient
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    @Transient
    @JsonIgnore
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
