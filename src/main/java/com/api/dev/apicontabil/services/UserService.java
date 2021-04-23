package com.api.dev.apicontabil.services;

import com.api.dev.apicontabil.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService extends CrudRepository<User, Integer> {
    Iterable<User> findByNomeAndEmail(String nome, String email);
    Iterable<User> findByNome(String nome);
    Iterable<User> findByEmail(String email);
    User findByLogin(String login);
    User findByLoginAndSenha(String login, String senha);


}
