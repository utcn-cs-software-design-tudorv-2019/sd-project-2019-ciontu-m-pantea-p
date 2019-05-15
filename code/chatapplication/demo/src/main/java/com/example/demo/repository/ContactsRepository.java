package com.example.demo.repository;


import com.example.demo.entities.Contacts;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<Contacts, Integer> {

}