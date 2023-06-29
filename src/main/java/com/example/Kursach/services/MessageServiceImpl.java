package com.example.Kursach.services;

import com.example.Kursach.repository.MessageRepository;
import com.example.Kursach.entities.Message;
import com.example.Kursach.entities.User;
import com.example.Kursach.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;



    public User getUserByPrincipal(Principal principal) { // по текущему авторизованному пользователю возвращаем пользователя из бд
        if (principal == null) {
            return new User();
        }
        return userRepository.findByEmail(principal.getName());
    }


    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    public List<Message> listMessages(String title) {
        if (title != null) return messageRepository.findByTitle(title);
        return messageRepository.findAll();
    }

    public void saveMessage(Principal principal, Message message) throws IOException {
         message.setUser(getUserByPrincipal(principal));
        messageRepository.save(message);
    }
}