package com.example.clickandcollectapi.repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.example.clickandcollectapi.*;
import com.example.clickandcollectapi.entities.Message;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MessageRepository extends CrudRepository<Message, Integer> {
    
}
