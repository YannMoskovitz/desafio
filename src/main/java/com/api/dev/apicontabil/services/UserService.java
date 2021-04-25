package com.api.dev.apicontabil.services;

import com.api.dev.apicontabil.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService extends JpaRepository<User, Integer> {
    Iterable<User> findByNomeAndEmail(String nome, String email);
    Iterable<User> findByNome(String nome);
    Iterable<User> findByEmail(String email);
    Iterable<User> findByLogin(String login);

}






