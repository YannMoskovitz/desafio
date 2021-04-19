package com.api.dev.apicontabil.services;

import com.api.dev.apicontabil.model.Client;

import org.springframework.data.repository.CrudRepository;

public interface ClientService extends CrudRepository<Client, Integer> {
    Iterable<Client> findByNomeAndCpfCnpjAndCidadeAndUf(String nome, String cpfCnpj, String cidade, char uf);
    Iterable<Client> findByNome(String nome);
    Iterable<Client> findByCpfCnpj(String cpfCnpj);
    Iterable<Client> findByCidade(String cidade);
    Iterable<Client> findByUf(char uf);

}
