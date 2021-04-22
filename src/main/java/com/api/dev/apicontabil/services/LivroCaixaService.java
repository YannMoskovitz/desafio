package com.api.dev.apicontabil.services;


import com.api.dev.apicontabil.model.LivroCaixa;
import org.springframework.data.repository.CrudRepository;

public interface LivroCaixaService extends CrudRepository<LivroCaixa, Integer>{
   Iterable<LivroCaixa> findByClient_Id(Integer idClient);
}
//User findByLoginAndSenha(String login, String senha);