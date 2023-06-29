package com.example.Kursach.controllers;

import com.example.Kursach.entities.User;
import com.example.Kursach.services.UserService;
import com.example.Kursach.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userServiceImpl;

    @GetMapping("/login") //get запрос по пути /login
    public String login(@RequestParam (name = "error", required = false) Object error, Model model) {
        if (error!=null) { //при выдаче ошибки выводит сообщение
            model.addAttribute("error", "Неправильный email или пароль");
        } //модель вставляет в специальное поле (атрибут error) в представлении сообщение
        return "login";
    }


    @GetMapping("/registration") // по гет запросу на путь /registration
    public String registration() {
        return "registration"; //вернуть страницу registration.html
    }

    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        if (!userServiceImpl.createUser(user)) {
            model.addAttribute("errorMessage", "Email занят");
            return "registration";
        }
        userServiceImpl.createUser(user);
        return "redirect:/login";
    }

    @PostMapping("/hello")
    public String securityUrl() {
        return "hello";
    }

}
