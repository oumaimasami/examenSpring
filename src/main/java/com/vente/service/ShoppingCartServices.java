package com.vente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vente.entities.CartItem;
import com.vente.entities.Product;
import com.vente.entities.User;
import com.vente.repository.CartItemRepository;

@Service
public class ShoppingCartServices {

	@Autowired
	private CartItemRepository cartRepo;
	
	public List<CartItem> listCartItems(User user){
		return cartRepo.findByUser(user);
	}
	public void addItemtoCart(CartItem item) {
		cartRepo.save(item);
	}
	public List<CartItem> getAllItems(){
		return cartRepo.findAll();
	}
}
