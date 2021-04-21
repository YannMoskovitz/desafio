package com.api.dev.apicontabil.controller;


import com.api.dev.apicontabil.model.Client;
import com.api.dev.apicontabil.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
                                 @RequestParam(value = "cpfCnpj", required = false) String cpfCnpj,
                                 @RequestParam(value = "cidade", required = false) String cidade,
                                 @RequestParam(value = "uf", required = false) String uf) {
        if (nome != null && cpfCnpj != null && cidade != null && uf != null )
            return clientService.findByNomeAndCpfCnpjAndCidadeAndUf(nome, cpfCnpj, cidade, uf);
        else if (nome != null)
            return clientService.findByNome(nome);
        else if (cpfCnpj != null)
            return  clientService.findByCpfCnpj(cpfCnpj);
        else if (cidade != null)
            return clientService.findByCidade(cidade);
        else if (uf != null)
            return clientService.findByUf(uf);
        else
            return clientService.findAll();
        // fazer else if para uf e outras regras de negocio.
        }

        @PostMapping("/client")
        Client create(@Valid @RequestBody Client client) {
            return clientService.save(client);
        }

        @PutMapping("/client")
        ResponseEntity<Client> update(@Valid @RequestBody Client client) {
            if (clientService.findById(client.getId()).isPresent())
                return new ResponseEntity(clientService.save(client), HttpStatus.OK);
            else
                return new ResponseEntity(client, HttpStatus.BAD_REQUEST);
        }



    @DeleteMapping("/client/{id}")
    void delete(@PathVariable Integer id) {
        clientService.deleteById(id);
    }
}
