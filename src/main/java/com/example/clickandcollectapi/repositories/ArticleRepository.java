package com.example.clickandcollectapi.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.clickandcollectapi.*;
import com.example.clickandcollectapi.entities.Article;
import com.example.clickandcollectapi.entities.TypeArticle;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ArticleRepository extends CrudRepository<Article, Integer> {

}
