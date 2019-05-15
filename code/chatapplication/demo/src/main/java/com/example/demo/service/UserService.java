package com.example.demo.service;

import java.util.List;

import javax.inject.Inject;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserService {

    @Inject
    UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getUsers()
    {
        List<User> userList = userRepository.findAll();


        ModelAndView mav = new ModelAndView("user_view");
        mav.addObject("users", userList);

        return mav;

    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView getUserById(@ModelAttribute(value = "userId") int userId)
    {
        User user = userRepository.getOne(userId);


        ModelAndView mav = new ModelAndView("user_view");
        mav.addObject("users", user);

        return mav;

    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView getUserwithMessages(@ModelAttribute(value = "userId") int userId)
    {
        User user = userRepository.getOne(userId);

        user.getMessages();

        ModelAndView mav = new ModelAndView("user_view");
        mav.addObject("users", user);

        return mav;

    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getUsersWithContacts()
    {
        List<User> userList = userRepository.findAll();
        for(int i = 0; i < userList.size() ; i++){
            userList.get(i).getContacts();
        }


        ModelAndView mav = new ModelAndView("user_view");
        mav.addObject("users", userList);

        return mav;

    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ModelAndView postTeacher(@ModelAttribute(value = "newUser") User newUser)
    {
        userRepository.save(newUser);

        return new ModelAndView("redirect:user");

    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ModelAndView updateUser(User oldUser, User updatedUser)
    {
        userRepository.delete(oldUser);

        userRepository.save(updatedUser);

        return new ModelAndView("redirect:user");

    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public ModelAndView deleteUser(User oldUser)
    {
        userRepository.delete(oldUser);

        return new ModelAndView("redirect:user");

    }

}
