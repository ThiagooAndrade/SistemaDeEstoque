package com.projetojava.sistemadeestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projetojava.sistemadeestoque.model.User;
import com.projetojava.sistemadeestoque.repository.UserRepository;
import com.projetojava.sistemadeestoque.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("login/register");
        return modelAndView;
    }

    @PostMapping("/saveUser")
    public ModelAndView registerUser(User user) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        userService.saveUser(user);
        modelAndView.setViewName("redirect:login/login");
        return modelAndView;
    }
}