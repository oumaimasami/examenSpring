package com.vente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vente.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

