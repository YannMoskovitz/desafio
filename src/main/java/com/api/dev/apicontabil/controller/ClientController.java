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
        }

        @PostMapping("/client")
        ResponseEntity<Client> create(@Valid @RequestBody Client client) {

        Iterable<Client> clientsComEsteEmail = clientService.findByEmail(client.getEmail());
        Iterable<Client> clientsComEsteTelefone = clientService.findByTelefone(client.getTelefone());
        Iterable<Client> clientsComEsteCpfcnpj = clientService.findByCpfCnpj(client.getCpfCnpj());

            if (clientsComEsteCpfcnpj.spliterator().getExactSizeIfKnown() > 0)
                return new ResponseEntity("Já existe um cliente com este Cpf ou cnpj", HttpStatus.BAD_REQUEST);
            if (clientsComEsteEmail.spliterator().getExactSizeIfKnown() > 0)
                return new ResponseEntity("Já existe um cliente com este endereço de email", HttpStatus.BAD_REQUEST);
            if (clientsComEsteTelefone.spliterator().getExactSizeIfKnown() > 0)
                return new ResponseEntity("Já existe um cliente com este numero de telefone", HttpStatus.BAD_REQUEST);
            return new ResponseEntity(clientService.save(client), HttpStatus.OK);
        }

        @PutMapping("/client")
        ResponseEntity<Client> update(@Valid @RequestBody Client client) {

            Iterable<Client> clientsComEsteEmail = clientService.findByEmail(client.getEmail());
            Iterable<Client> clientsComEsteTelefone = clientService.findByTelefone(client.getTelefone());
            Iterable<Client> clientsComEsteCpfcnpj = clientService.findByCpfCnpj(client.getCpfCnpj());

            if (!clientService.findById(client.getId()).isPresent())
                return new ResponseEntity("Usuario com este ID não encontrado", HttpStatus.NOT_FOUND);
            if (clientsComEsteCpfcnpj.spliterator().getExactSizeIfKnown() > 0)
                return new ResponseEntity("Já existe um cliente com este Cpf ou cnpj", HttpStatus.BAD_REQUEST);
            if (clientsComEsteEmail.spliterator().getExactSizeIfKnown() > 0)
                return new ResponseEntity("Já existe um cliente com este endereço de email", HttpStatus.BAD_REQUEST);
            if (clientsComEsteTelefone.spliterator().getExactSizeIfKnown() > 0)
                return new ResponseEntity("Já existe um cliente com este numero de telefone", HttpStatus.BAD_REQUEST);
            return new ResponseEntity(clientService.save(client), HttpStatus.OK);

    }



    @DeleteMapping("/client/{id}")
    ResponseEntity<Client> delete(@PathVariable Integer id) {
        if (clientService.findById(id).isPresent()) {
            clientService.deleteById(id);
            return new ResponseEntity("Cliente deletado com sucesso", HttpStatus.OK);}
        else
            return new ResponseEntity("Cliente com este id não existe", HttpStatus.NOT_FOUND);
    }
}
