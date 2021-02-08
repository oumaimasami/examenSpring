package com.vente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vente.entities.Product;
import com.vente.repository.ProductRepository;
@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	public void addProduct(Product product) {
		productRepository.save(product);	
	}
	public void removeProduct(Long id) {
		productRepository.deleteById(id);
	}
	public Optional<Product> getProduct(Long id) {
		return productRepository.findById(id);
	}
	public List<Product> getProductsByCategory(int id){
		return productRepository.findAllByCategory_id(id);
	}

}
