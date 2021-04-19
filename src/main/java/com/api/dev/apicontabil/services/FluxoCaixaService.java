package com.api.dev.apicontabil.services;

import com.api.dev.apicontabil.model.LivroCaixa;
import org.springframework.data.repository.CrudRepository;

public interface FluxoCaixaService extends CrudRepository<LivroCaixa, Integer> {
    Iterable<LivroCaixa> findByValor(String valor);
}
