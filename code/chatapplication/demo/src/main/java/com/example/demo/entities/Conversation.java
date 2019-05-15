package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int creator_id;
    private int chanel_id;

    @ManyToOne
    @JoinColumn(name = "creatingUser")
    @NotNull
    private User creatingUser;

    @ManyToMany
    @NotNull
    private List<User> users;

    public Conversation(){}

    public Conversation(String title, int creator_id, int chanel_id){
        this.title=title;
        this.creator_id=creator_id;
        this.chanel_id=chanel_id;
    }
}
