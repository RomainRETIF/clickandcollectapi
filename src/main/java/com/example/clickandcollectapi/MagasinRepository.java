package com.example.clickandcollectapi;

import org.springframework.data.repository.CrudRepository;

import com.example.clickandcollectapi.*;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MagasinRepository extends CrudRepository<Magasin, Integer> {

}
