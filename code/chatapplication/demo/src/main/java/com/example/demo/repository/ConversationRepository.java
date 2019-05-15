package com.example.demo.repository;


import com.example.demo.entities.Conversation;
import com.example.demo.entities.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Integer> {

}