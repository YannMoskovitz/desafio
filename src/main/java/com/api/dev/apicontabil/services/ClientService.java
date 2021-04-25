package com.api.dev.apicontabil.services;

import com.api.dev.apicontabil.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientService extends JpaRepository<Client, Integer> {
    Iterable<Client> findByNomeAndCpfCnpjAndCidadeAndUf(String nome, String cpfCnpj, String cidade, String uf);
    Iterable<Client> findByNome(String nome);
    Iterable<Client> findByCpfCnpj(String cpfCnpj);
    Iterable<Client> findByCidade(String cidade);
    Iterable<Client> findByUf(String uf);
    Iterable<Client> findByTelefone(String telefone);
    Iterable<Client> findByEmail(String email);

}
