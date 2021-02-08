package com.vente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vente.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findAllByCategory_id(int id);

}
