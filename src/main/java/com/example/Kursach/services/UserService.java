package com.example.Kursach.services;

import com.example.Kursach.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    boolean createUser(User user);
    public List<User> list();
}
