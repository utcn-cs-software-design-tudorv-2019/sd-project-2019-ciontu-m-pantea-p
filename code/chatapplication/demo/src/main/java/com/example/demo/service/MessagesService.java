package com.example.demo.service;

import java.util.List;

import javax.inject.Inject;

import com.example.demo.entities.Messages;
import com.example.demo.repository.MessagesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessagesService {

    @Inject
    MessagesRepository messagesRepository;

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ModelAndView getMessages()
    {
        List<Messages> messagesList = messagesRepository.findAll();


        ModelAndView mav = new ModelAndView("messages_view");
        mav.addObject("messages", messagesList);

        return mav;

    }

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ModelAndView getMessagesById(@ModelAttribute(value = "messagesId") int messagesId)
    {
        Messages messages = messagesRepository.getOne(messagesId);


        ModelAndView mav = new ModelAndView("messages_view");
        mav.addObject("messages", messages);

        return mav;

    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public ModelAndView postMessages(@ModelAttribute(value = "newMessage") Messages newMessage)
    {
        messagesRepository.save(newMessage);

        return new ModelAndView("redirect:message");

    }

    @RequestMapping(value = "/messages", method = RequestMethod.PUT)
    public ModelAndView updateMessages(Messages oldMessage, Messages updatedMessage)
    {
        messagesRepository.delete(oldMessage);

        messagesRepository.save(updatedMessage);

        return new ModelAndView("redirect:message");

    }

    @RequestMapping(value = "/messages", method = RequestMethod.DELETE)
    public ModelAndView deleteMessages(Messages oldMessage)
    {
        messagesRepository.delete(oldMessage);

        return new ModelAndView("redirect:message");

    }

}
