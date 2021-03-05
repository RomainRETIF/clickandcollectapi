package com.example.clickandcollectapi.repositories;

import com.example.clickandcollectapi.entities.Commande;

import org.springframework.data.repository.CrudRepository;

public interface CommandeRepository extends CrudRepository<Commande, Integer> {
    
}
