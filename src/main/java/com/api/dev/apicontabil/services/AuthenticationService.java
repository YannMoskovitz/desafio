package com.api.dev.apicontabil.services;

import com.api.dev.apicontabil.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface AuthenticationService extends CrudRepository<User, Integer> {
    User findByLoginAndSenha(String login, String senha);
}
