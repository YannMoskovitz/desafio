package com.api.dev.apicontabil.services;

import com.api.dev.apicontabil.model.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientService extends CrudRepository<Client, Integer> {
}
