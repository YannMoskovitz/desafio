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
    ResponseEntity login( @RequestBody User user) {

        User userInstance = userService.findByLoginAndSenha(user.getLogin(), user.getSenha());

        if (userInstance == null)
            return new ResponseEntity("Usuario e/ou senha incorretos", HttpStatus.BAD_REQUEST);
        if (userInstance.getStatus() == null)
            return new ResponseEntity("Usuario não possui status definido", HttpStatus.BAD_REQUEST);
        if (userInstance.getStatus() == Status.A)
            return new ResponseEntity("Usuario logado com sucesso", HttpStatus.OK);

        return new ResponseEntity("Usuario está inativo/cancelado", HttpStatus.BAD_REQUEST);
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

        Iterable<User> UsuariosComEsteEmail = userService.findByEmail(user.getEmail());
        Iterable<User> UsuariosComEsteLogin = userService.findByLogin(user.getLogin());

        if (!userService.findById(user.getId()).isPresent())
            return new ResponseEntity("Usuário com este ID não encontrado" , HttpStatus.NOT_FOUND);

        if (UsuariosComEsteEmail.spliterator().getExactSizeIfKnown() > 0)
            return new ResponseEntity("Este e-mail já esta em uso" , HttpStatus.BAD_REQUEST);

        if (UsuariosComEsteLogin.spliterator().getExactSizeIfKnown() > 0)
            return new ResponseEntity("Este Login já esta em uso" , HttpStatus.BAD_REQUEST);

        return new ResponseEntity(userService.save(user), HttpStatus.OK);

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
