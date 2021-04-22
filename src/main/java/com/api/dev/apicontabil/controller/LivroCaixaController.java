package com.api.dev.apicontabil.controller;

import com.api.dev.apicontabil.model.LivroCaixa;
import com.api.dev.apicontabil.services.LivroCaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    Optional<LivroCaixa> findById(@PathVariable Integer id) {
        return livroCaixaService.findById(id);
    }

    @GetMapping("/livro/search")
    Iterable<LivroCaixa> findByQuery(@RequestParam(value = "idClient", required = false) Integer idClient){
        return livroCaixaService.findByIdClient(idClient);
    }




    @PostMapping("/livro")
    LivroCaixa create(@RequestBody LivroCaixa livroCaixa) {
        return livroCaixaService.save(livroCaixa);
    }

    @PutMapping("/livro")
    ResponseEntity<LivroCaixa> update(@Valid @RequestBody LivroCaixa livroCaixa) {

        if (livroCaixaService.findById(livroCaixa.getId()).isPresent())
            return new ResponseEntity(livroCaixaService.save(livroCaixa), HttpStatus.OK);
        else
            return new ResponseEntity("O livro do qual voce tentou atualizar não existe", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/livro/{id}")
    void delete(@PathVariable Integer id) {
        livroCaixaService.deleteById(id);
    }
}
