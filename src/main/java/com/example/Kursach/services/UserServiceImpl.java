package com.example.Kursach.services;

import com.example.Kursach.enums.Role;
import com.example.Kursach.entities.User;
import com.example.Kursach.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository; //импортируем репозиторий
    private final PasswordEncoder passwordEncoder; //импортируем passwordencoder

    public boolean createUser(User user) { // создание нового юзера
        String email = user.getEmail(); //по емейлу
        if (userRepository.findByEmail(email) != null) { // проверяем есть ли такой пользователь
            return false; //если есть то возвращаем false
        }
        if (userRepository.findByEmail("admin@mail.ru")==null){ //в проекте для упрощения было принято
            user.setPassword(passwordEncoder.encode(user.getPassword()));//реализовать создание пользователя с ролью admin
            //по почему "admin@mail.ru"
            user.getRoles().add(Role.ROLE_ADMIN);
            userRepository.save(user);//сохраняем в репозиторий (базу данных)
            return true;
        }
        else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.getRoles().add(Role.ROLE_USER);
            userRepository.save(user);
            return true;
        }
    }

    public List<User> list() {
        return userRepository.findAll();
    }
}
