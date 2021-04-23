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
//    @PostMapping("/usuario")
//    ResponseEntity<User> create(@Valid @RequestBody User user) {
//        if (userService.findByEmail(user.getEmail()) == null )
//            return new ResponseEntity(userService.save(user), HttpStatus.OK);
//        else if (userService.findByLogin(user.getLogin()) == null)
//            return new ResponseEntity(userService.save(user), HttpStatus.OK);
//        else
//            return new ResponseEntity("Usuario com estas credenciais já existe", HttpStatus.OK);
        @PostMapping("/client")
        ResponseEntity<Client> create(@Valid @RequestBody Client client) {

            Client clientByEmail = clientService.findByEmail(client.getEmail());
            Client clientByCpfCnpj = clientService.findByEmailAndCpfCnpj(client.getEmail(), client.getCpfCnpj());
            Client clientByTelefone = clientService.findByTelefone(client.getTelefone());

            if (clientByCpfCnpj == null && clientByTelefone == null && clientByEmail == null)
                return new ResponseEntity(clientService.save(client), HttpStatus.OK);
            else
                return new ResponseEntity("Cliente com estas credenciais já existe", HttpStatus.BAD_REQUEST);
        }

        @PutMapping("/client")
        ResponseEntity<Client> update(@Valid @RequestBody Client client) {

//            Client clientByEmail = clientService.findByEmail(client.getEmail());
//            Client clientByCpfCnpj = clientService.findByEmailAndCpfCnpj(client.getEmail(), client.getCpfCnpj());
//            Client clientByTelefone = clientService.findByTelefone(client.getTelefone());

            if (clientService.findById(client.getId()).isPresent())  {
//                if (clientService.findByEmail(client.getEmail()) == null
//                        || clientService.findByCpfCnpj(client.getCpfCnpj()) == null
//                        || clientService.findByTelefone(client.getTelefone()) == null)
                    return new ResponseEntity(clientService.save(client), HttpStatus.OK);
//                else
//                    return new ResponseEntity("Cliente com estas credenciais já existe", HttpStatus.BAD_REQUEST);
            }
            else
                return new ResponseEntity("Cliente com este id não existe", HttpStatus.NOT_FOUND);
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
