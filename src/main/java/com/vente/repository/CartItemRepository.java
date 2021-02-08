package com.vente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vente.entities.CartItem;
import com.vente.entities.User;



@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	public List<CartItem> findByUser(User user);
}
