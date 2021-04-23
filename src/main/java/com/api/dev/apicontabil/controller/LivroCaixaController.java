package com.api.dev.apicontabil.controller;

import com.api.dev.apicontabil.model.LivroCaixa;
import com.api.dev.apicontabil.services.ClientService;
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

    @Autowired
    ClientService clientService;

    @GetMapping("/livro")
    Iterable<LivroCaixa> read(){
        return livroCaixaService.findAll();
    }

    @GetMapping("/livro/{id}")
    Optional<LivroCaixa> findById(@Valid @PathVariable Integer id) {
        return livroCaixaService.findById(id);
    }

    @GetMapping("/livro/search")
    Iterable<LivroCaixa> findByQuery(@Valid @RequestParam(value = "idClient", required = false) Integer idClient){
        return livroCaixaService.findByClient_Id(idClient);
    }


    @PostMapping("/livro")
    ResponseEntity<LivroCaixa> create(@Valid @RequestBody LivroCaixa livroCaixa) {
            return new ResponseEntity(livroCaixaService.save(livroCaixa), HttpStatus.CREATED);
    }

    @PutMapping("/livro")
    ResponseEntity<LivroCaixa> update(@Valid @RequestBody LivroCaixa livroCaixa) {

        if (livroCaixaService.findById(livroCaixa.getId()).isPresent())
            return new ResponseEntity(livroCaixaService.save(livroCaixa), HttpStatus.OK);
        else
            return new ResponseEntity("O livro do qual voce tentou atualizar não existe", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/livro/{id}")
            ResponseEntity<LivroCaixa> delete(@PathVariable Integer id) {
                if (livroCaixaService.findById(id).isPresent()){
                        livroCaixaService.deleteById(id);
                    return new ResponseEntity("Livro caixa deletado com sucesso", HttpStatus.OK);}
                else
                    return new ResponseEntity("Usuario com este id não existe", HttpStatus.NOT_FOUND);}
}
