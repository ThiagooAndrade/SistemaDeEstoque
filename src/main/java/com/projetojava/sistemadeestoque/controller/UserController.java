package com.projetojava.sistemadeestoque.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import com.projetojava.sistemadeestoque.exception.ServiceException;
import com.projetojava.sistemadeestoque.model.Product;
import com.projetojava.sistemadeestoque.model.User;
import com.projetojava.sistemadeestoque.repository.UserRepository;
import com.projetojava.sistemadeestoque.service.UserService;
import com.projetojava.sistemadeestoque.util.Util;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home/index");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    // @GetMapping("/register")
    // public ModelAndView register() {
    // ModelAndView modelAndView = new ModelAndView();
    // modelAndView.addObject("user", new User());
    // modelAndView.setViewName("login/register");
    // return modelAndView;
    // }

    // @PostMapping("/saveUser")
    // public ModelAndView register(User user) throws Exception {
    // ModelAndView modelAndView = new ModelAndView();
    // userService.saveUser(user);
    // modelAndView.setViewName("redirect:/");
    // return modelAndView;
    // }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("login/login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(@Valid User user, BindingResult br,
            HttpSession session) throws NoSuchAlgorithmException, ServiceException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        if (br.hasErrors()) {
            modelAndView.setViewName("login/login");
        }

        User userLogin = userService.loginUser(user.getUsername(), Util.md5(user.getPassword()));
        if (userLogin == null) {
            modelAndView.addObject("msg", "Usuario não encontrado. Tente novamente");
        } else {
            session.setAttribute("userLogged", userLogin);
            return index();
        }

        return modelAndView;
    }

    @PostMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return login();
    }

}
