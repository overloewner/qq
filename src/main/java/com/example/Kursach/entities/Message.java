package com.example.Kursach.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data// создает геттеры и сеттеры

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String text;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, //делаем связь в таблице с автором сообщения (user_id)
            mappedBy = "message")

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    public String getAuthor(){
        return user.getEmail();
    }
    public String getNameAuthor(){return user.getName();} //методы для получаения имени автора
}
