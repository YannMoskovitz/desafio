package com.api.dev.apicontabil.services;

import com.api.dev.apicontabil.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserService extends CrudRepository<User, Integer> {

    Iterable<User> findByNomeAndEmail(String nome, String email);
    Iterable<User> findByNome(String nome);
    Iterable<User> findByEmail(String email);
}
