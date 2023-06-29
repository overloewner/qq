package com.example.Kursach.entities;

import com.example.Kursach.enums.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


@Entity //аннотация указывает спрингу на то что из данного класса нужно создать таблицу
@Table(name = "users")//название таблицы
@Data//аннотация lombok которая создает геттеры и сеттеры
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // разрешаем спрингу распределяться генерацией id
    private Long id;
    @Column(name = "email", unique = true) //уникальный параметр
    private String email;
    private String phoneNumber;
    private String name;
    private String password;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) //связываем наших юзеров с ролью в отдельной таблице
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))// таблица будет типа role-user_id
    @Enumerated(EnumType.STRING)//достаем стринговое значение роли из enum
    private Set<Role> roles = new HashSet<>();


    public boolean isAdmin() {
        return roles.contains(Role.ROLE_ADMIN);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}