package com.api.dev.apicontabil.controller;


import com.api.dev.apicontabil.model.User;
import com.api.dev.apicontabil.services.UserService;
import com.api.dev.apicontabil.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @PostMapping("/usuario")
    User create(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/usuario")
    ResponseEntity<User> update(@Valid @RequestBody User user) {

        if (userService.findById(user.getId()).isPresent()) // userService.findByID(user.getId() tenta encontrar um id, ao acionar o .isPresente() faz uma verificação boolean retornando true caso o getId retorne algo)
            return new ResponseEntity(userService.save(user), HttpStatus.OK);
        else
            return new ResponseEntity(user , HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
     List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
      List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
      List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessages;
    }

    @DeleteMapping("/usuario/{id}")
    void delete(@PathVariable Integer id) {
        userService.deleteById(id);
    }

}
