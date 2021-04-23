package com.api.dev.apicontabil.controller;


import com.api.dev.apicontabil.model.Status;
import com.api.dev.apicontabil.model.User;
import com.api.dev.apicontabil.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    // -------------------------------------------- METODOS GET --------------------------------------------------- //
    @GetMapping("/usuario")
    Iterable<User> read() {
        return userService.findAll();
    }

    @GetMapping("/usuario/{id}")
    Optional<User> findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @GetMapping("usuario/search")
    Iterable<User> findByQuery(@RequestParam(value = "nome",required = false) String nome,
                               @RequestParam(value = "email", required = false) String email) {
        if (nome != null && email != null)
            return userService.findByNomeAndEmail(nome, email);
        else if (nome != null)
            return userService.findByNome(nome);
        else if (email != null)

            return  userService.findByEmail(email);
        else
            return userService.findAll();
    }

    @PostMapping("/login")
    ResponseEntity login(@Valid @RequestBody User user) {

        User userInstance = userService.findByLoginAndSenha(user.getLogin(), user.getSenha());

            if( userInstance != null && userInstance.getStatus() == Status.A)
                return new ResponseEntity("Usuario autenticado", HttpStatus.OK);
            else if (userInstance != null && userInstance.getStatus() == Status.C)
                return new ResponseEntity("Usuario inativo/cancelado", HttpStatus.OK);
            else
                return new ResponseEntity("Login e/ou senha invalidos", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/usuario")
    ResponseEntity<User> create(@Valid @RequestBody User user) {

           if (userService.findByEmail(user.getEmail()) == null || userService.findByLogin(user.getLogin()) == null)
               return new ResponseEntity(userService.save(user), HttpStatus.OK);
           else
               return new ResponseEntity("Usuario com estas credenciais já existe", HttpStatus.OK);
    }

    @PutMapping("/usuario")
    ResponseEntity<User> update(@Valid @RequestBody User user) {

        if (userService.findById(user.getId()).isPresent()) {
            if (userService.findByEmail(user.getEmail()) == null || userService.findByLogin(user.getLogin()) == null)
                return new ResponseEntity(userService.save(user), HttpStatus.OK);
            else
                return new ResponseEntity("Credenciais e-mail e/ou login já estão em uso\n ou outras informações não puderam ser atualizadas" , HttpStatus.BAD_REQUEST);}
        else
            return new ResponseEntity("Usuario com este id não existe" , HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping("/usuario/{id}")
    ResponseEntity<User> delete(@PathVariable Integer id) {
        if (userService.findById(id).isPresent()) {
            userService.deleteById(id);
            return new ResponseEntity("Usuario deletado com sucesso", HttpStatus.OK);}
        else
            return new ResponseEntity("Usuario com este id não existe", HttpStatus.NOT_FOUND);
    }
}
