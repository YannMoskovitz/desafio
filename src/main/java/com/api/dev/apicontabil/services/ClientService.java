package com.api.dev.apicontabil.services;

import com.api.dev.apicontabil.model.Client;

import org.springframework.data.repository.CrudRepository;

public interface ClientService extends CrudRepository<Client, Integer> {
    Iterable<Client> findByNomeAndEmail(String nome, String email);
    Iterable<Client> findByNome(String nome);
    Iterable<Client> findByEmail(String email);
}
