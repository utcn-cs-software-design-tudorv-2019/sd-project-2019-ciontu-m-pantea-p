package com.example.demo.service;

import java.util.List;

import javax.inject.Inject;

import com.example.demo.entities.Conversation;
import com.example.demo.repository.ConversationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConversationService {

    @Inject
    ConversationRepository conversationRepository;

    @RequestMapping(value = "/conversation", method = RequestMethod.GET)
    public ModelAndView getConversation()
    {
        List<Conversation> conversationsList = conversationRepository.findAll();


        ModelAndView mav = new ModelAndView("conversation_view");
        mav.addObject("conversation", conversationsList);

        return mav;

    }

    @RequestMapping(value = "/conversation", method = RequestMethod.GET)
    public ModelAndView getConversationById(@ModelAttribute(value = "conversationId") int conversationId)
    {
        Conversation conversation = conversationRepository.getOne(conversationId);


        ModelAndView mav = new ModelAndView("conversation_view");
        mav.addObject("conversation", conversation);
        return mav;

    }

    @RequestMapping(value = "/conversation", method = RequestMethod.POST)
    public ModelAndView postConversation(@ModelAttribute(value = "newConversation") Conversation newConversation)
    {
        conversationRepository.save(newConversation);

        return new ModelAndView("redirect:conversation");

    }

    @RequestMapping(value = "/conversation", method = RequestMethod.PUT)
    public ModelAndView updateConversation(Conversation oldConversation, Conversation updatedConversation)
    {
        conversationRepository.delete(oldConversation);

        conversationRepository.save(updatedConversation);

        return new ModelAndView("redirect:conversation");

    }

    @RequestMapping(value = "/conversation", method = RequestMethod.DELETE)
    public ModelAndView deleteConversation(Conversation oldConversation)
    {
        conversationRepository.delete(oldConversation);

        return new ModelAndView("redirect:user");

    }

}
