package com.projetojava.sistemadeestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projetojava.sistemadeestoque.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select e from User e where e.email = :email")
    public User findByEmail(String email);

    @Query("select e from User e where e.username = :username")
    public User findByUsername(String username);

    @Query("select l from User l where l.username = :username and l.password = :password")
    public User findLogin(String username, String password);
}
