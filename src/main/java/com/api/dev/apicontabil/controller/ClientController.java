package com.api.dev.apicontabil.controller;


import com.api.dev.apicontabil.model.Client;
import com.api.dev.apicontabil.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/client")
    Iterable<Client> read() {
        return clientService.findAll();
    }

    @GetMapping("/client/{id}")
    Optional<Client> findById(@PathVariable Integer id) {
        return clientService.findById(id);
    }

    @GetMapping("client/search")
    Iterable<Client> findByQuery(@RequestParam(value = "nome",required = false) String nome,
                                 @RequestParam(value = "email", required = false) String email) {
        
        if (nome != null && email != null)
            return clientService.findByNomeAndEmail(nome, email);
        else if (nome != null)
            return clientService.findByNome(nome);
        else if (email != null)
            return  clientService.findByEmail(email);
        else
            return clientService.findAll();
        }
}
