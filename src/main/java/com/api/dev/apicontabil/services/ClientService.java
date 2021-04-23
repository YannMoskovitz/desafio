package com.api.dev.apicontabil.services;

import com.api.dev.apicontabil.model.Client;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientService extends CrudRepository<Client, Integer> {
    Iterable<Client> findByNomeAndCpfCnpjAndCidadeAndUf(String nome, String cpfCnpj, String cidade, String uf);
    Iterable<Client> findByNome(String nome);
    Iterable<Client> findByCpfCnpj(String cpfCnpj);
    //Client findByCpfcnpj(String cpfCnpj);
    Iterable<Client> findByCidade(String cidade);
    Iterable<Client> findByUf(String uf);
    Client findByTelefone(String telefone);
    Client findByEmail(String email);
    Client findByEmailAndCpfCnpj(String email, String cpfCnpj);
}
