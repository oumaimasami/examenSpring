package com.vente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vente.entities.Category;
import com.vente.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	public List<Category> getAllCategory(){
		return categoryRepository.findAll();
	}
	public void addCategory(Category category) {
       categoryRepository.save(category);
	}
	public void removeCategory(int id) {
		categoryRepository.deleteById(id);
	}
	public Optional<Category> getCategory(int id){
		return categoryRepository.findById(id);
	}
	

}
