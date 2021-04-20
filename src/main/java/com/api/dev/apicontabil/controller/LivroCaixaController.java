package com.api.dev.apicontabil.controller;

import com.api.dev.apicontabil.model.Client;
import com.api.dev.apicontabil.model.LivroCaixa;
import com.api.dev.apicontabil.services.LivroCaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LivroCaixaController {

    @Autowired
    LivroCaixaService livroCaixaService;

    @GetMapping("/livro")
    Iterable<LivroCaixa> read(){
        return livroCaixaService.findAll();
    }

    @GetMapping("/livro/{id}")
    Optional<LivroCaixa> findById(@PathVariable Integer id){
        return livroCaixaService.findById(id);
    }

    @GetMapping("livro/search")
    Iterable<LivroCaixa> findByQuery(@RequestParam (value = "client_id", required = false) Client client_id){
        if (livroCaixaService.findById(client_id.getId()).isPresent())
            return livroCaixaService.findByClient_id(client_id);
        else
            return livroCaixaService.findAll();
    }

    @PostMapping("/livro")
    LivroCaixa create(@RequestBody LivroCaixa livroCaixa) {
        return livroCaixaService.save(livroCaixa);
    }

    @PutMapping("/livro")
    ResponseEntity<LivroCaixa> update(@RequestBody LivroCaixa livroCaixa) {
        if (livroCaixaService.findById(livroCaixa.getId()).isPresent()) // userService.findByID(user.getId() tenta encontrar um id, ao acionar o .isPresente() faz uma verificação boolean retornando true caso o getId retorne algo)
            return new ResponseEntity(livroCaixaService.save(livroCaixa), HttpStatus.OK);
        else
            return new ResponseEntity(livroCaixa , HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("livro/{id}")
    void delete(@PathVariable Integer id) {livroCaixaService.deleteById(id);}

}
