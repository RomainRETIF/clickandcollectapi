package com.example.clickandcollectapi.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.clickandcollectapi.*;
import com.example.clickandcollectapi.entities.Article;
import com.example.clickandcollectapi.entities.Contenir;
import com.example.clickandcollectapi.entities.Creneau;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CreneauRepository extends CrudRepository<Creneau, Integer> {

}
