package com.api.dev.apicontabil.services;


import com.api.dev.apicontabil.model.LivroCaixa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroCaixaService extends CrudRepository<LivroCaixa, Integer> {
//   @Query("SELECT t FROM LivroCaixa t WHERE t.client.id = :client")
//   List<LivroCaixa> findIdClient (Integer idClient);
   Iterable<LivroCaixa> findByClient_Id(Integer idClient);


}
//User findByLoginAndSenha(String login, String senha);