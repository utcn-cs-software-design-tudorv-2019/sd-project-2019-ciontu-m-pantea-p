package com.example.demo.service;

import com.example.demo.entities.Contacts;
import com.example.demo.repository.ContactsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.List;

@Controller
public class ContactsService {

    @Inject
    ContactsRepository contactsRepository;
    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView getContacts(){
        List<Contacts> contactsList = contactsRepository.findAll();


        ModelAndView mav = new ModelAndView("contacts_view");
        mav.addObject("users", contactsList);

        return mav;
    }
    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView getUserById(@ModelAttribute(value = "contactsId") int contactsId)
    {
        Contacts contacts = contactsRepository.getOne(contactsId);


        ModelAndView mav = new ModelAndView("contacts_view");
        mav.addObject("contacts", contacts);

        return mav;

    }
    @RequestMapping(value = "/contacts", method = RequestMethod.POST)
    public ModelAndView postContacts(@ModelAttribute(value = "newContacts") Contacts newContact)
    {
        contactsRepository.save(newContact);

        return new ModelAndView("redirect:contact");

    }
    @RequestMapping(value = "/contacts", method = RequestMethod.PUT)
    public ModelAndView updateContact(Contacts oldContact, Contacts updateContact)
    {
        contactsRepository.delete(oldContact);

        contactsRepository.save(updateContact);

        return new ModelAndView("redirect:contact");

    }
    @RequestMapping(value = "/contacts", method = RequestMethod.DELETE)
    public ModelAndView deleteContact(Contacts oldContact)
    {
        contactsRepository.delete(oldContact);

        return new ModelAndView("redirect:contact");

    }


}
