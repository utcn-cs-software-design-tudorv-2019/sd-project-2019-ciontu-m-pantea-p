package com.example.demo.entities;

import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "chatUser")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String phone;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String verificationCode;
    private Boolean Is_active;
    private Boolean Is_blocked;
    private Boolean Is_reported;
    private Boolean Is_admin;

    @OneToMany(mappedBy = "chatUser")
    private List<Contacts> contacts;

    @OneToMany(mappedBy = "chatUser")
    private List<Messages> messages;

//    @OneToMany(mappedBy = "chatUser", fetch = FetchType.EAGER)
//    private List<Conversation> conversations;

    @ManyToMany
    private List<Conversation> conversations;

    public User(){}

    public User(String phone, String email, String password, String firstName, String lastName, String verificationCode, Boolean Is_active, Boolean Is_blocked, Boolean Is_reported, Boolean Is_admin) {
        this.phone = phone;
        this.email=email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.verificationCode=verificationCode;
        this.Is_active=Is_active;
        this.Is_blocked=Is_blocked;
        this.Is_reported=Is_reported;
        this.Is_admin=Is_admin;
    }

    @Override
    public String toString(){
         return "User(" + firstName + ", " + lastName + ")\n";
     }

     @Override
    public boolean equals(Object obj){
        return false;
     }

}
