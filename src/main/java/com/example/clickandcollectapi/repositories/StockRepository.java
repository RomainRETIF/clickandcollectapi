package com.example.clickandcollectapi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.clickandcollectapi.*;
import com.example.clickandcollectapi.entities.Stock;

public interface StockRepository extends CrudRepository<Stock, Integer> {
    
}
