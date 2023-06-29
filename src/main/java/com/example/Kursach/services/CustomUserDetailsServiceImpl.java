package com.example.Kursach.services;

import com.example.Kursach.repository.UserRepository;
import com.example.Kursach.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),// на основании полученных данных формируем объект UserDetails
                    // который позволит проверить введенный пользователем логин и пароль
                    // и уже потом аутентифицировать пользователя
                    user.getPassword(),
                    user.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getAuthority()))
                            .collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("неверный логин или пароль");
        }
    }
}
