package com.projetojava.sistemadeestoque.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetojava.sistemadeestoque.exception.CriptoExistsException;
import com.projetojava.sistemadeestoque.exception.EmailExistsException;
import com.projetojava.sistemadeestoque.exception.ServiceException;
import com.projetojava.sistemadeestoque.exception.UsernameExistsException;
import com.projetojava.sistemadeestoque.model.User;
import com.projetojava.sistemadeestoque.repository.UserRepository;
import com.projetojava.sistemadeestoque.util.Util;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) throws Exception {

        try {

            if (userRepository.findByEmail(user.getEmail()) != null) {
                throw new EmailExistsException("Este email já esta cadastrado: " + user.getEmail());
            }

            if (userRepository.findByUsername(user.getUsername()) != null) {
                throw new UsernameExistsException("Este username já está cadastrado: " + user.getUsername());
            }

            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            // user.setPassword(Util.md5(user.getPassword()));

        } finally {
            System.out.println("Finally");
        }
        userRepository.save(user);
        // catch (NoSuchAlgorithmException e) {
        // throw new CriptoExistsException("Error na criptografia da senha");
        // }
    }

    public User loginUser(String user, String senha) throws ServiceException {

        return userRepository.findLogin(user, senha);
    }
}
