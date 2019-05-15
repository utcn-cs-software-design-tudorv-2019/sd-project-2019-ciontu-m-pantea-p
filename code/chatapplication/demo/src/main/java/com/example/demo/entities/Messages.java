package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    private int conversation_id;
//    private int sender_id;
//    private String type;
    private String text;

    @ManyToOne
    @JoinColumn(name = "chatUser")
    @NotNull
    private User chatUser;

    public Messages(){}

}
