package com.projetojava.sistemadeestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projetojava.sistemadeestoque.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select e from User e where e.email = :email")
    public User findByEmail(String email);

    @Query("select l from User l where l.user = :user and l.password = :password")
    public User findLogin(String user, String password);
}
