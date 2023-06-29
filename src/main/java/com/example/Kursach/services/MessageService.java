package com.example.Kursach.services;

import com.example.Kursach.entities.Message;
import com.example.Kursach.entities.User;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface MessageService {
    List<Message> listMessages(String title);
    User getUserByPrincipal(Principal principal);

    void saveMessage(Principal principal, Message message) throws IOException;

    Message getMessageById(Long id);

    void deleteMessage(Long newid);
}
