package com.example.Kursach.repository;

import com.example.Kursach.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> { //Jpa репозиторий включает в себя базовые комманды для поиска
    List<Message> findByTitle(String title); //спринг понимает такой формат и сам реализует метод

}
