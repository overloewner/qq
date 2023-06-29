package com.example.Kursach.controllers;

import com.example.Kursach.entities.Message;
import com.example.Kursach.services.MessageService;
import com.example.Kursach.services.MessageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageServiceImpl;

    @GetMapping("/") //гет запрос по пути /
    public String messages(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("messages", messageServiceImpl.listMessages(title)); //кладем список сообщений в атрибут модели для вывода
        model.addAttribute("user", messageServiceImpl.getUserByPrincipal(principal));//кладем в поле user текущего пользователя
        if (title == "")
        {
            return "redirect:/"; //перенаправление на url /
        }
        return "forum";
    }

    @GetMapping("/message/{id}") //аннотация PathVariable динамически вставит в id в поле пути
    public String Messageid(@PathVariable Long id, Model model, Principal principal) {
        Message message = messageServiceImpl.getMessageById(id);
        model.addAttribute("user", messageServiceImpl.getUserByPrincipal(principal));
        model.addAttribute("message", message);
        return "message-id";
    }

    @PostMapping("/message/create")
    public String createMessage(Message message, Principal principal) throws IOException {
        messageServiceImpl.saveMessage (principal, message);
        return "redirect:/";
    }

    @PostMapping("/message/delete/{id}")
    public String deleteMessage(@PathVariable String id) {
        Long newid = Long.valueOf(id);
        messageServiceImpl.deleteMessage(newid);
        return "redirect:/";
    }
}