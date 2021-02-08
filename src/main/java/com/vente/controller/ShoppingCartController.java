package com.vente.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vente.entities.CartItem;
import com.vente.entities.Product;
import com.vente.entities.User;
import com.vente.service.ProductService;
import com.vente.service.ShoppingCartServices;
import com.vente.service.UserService;

@Controller
public class ShoppingCartController {
	
@Autowired
private ShoppingCartServices cartServices;

@Autowired
private UserService userService;
@Autowired
private ProductService proServ;

@GetMapping("/cart/{id}")
public String showShoppingCart(@PathVariable("id") long id,Model model,
		@AuthenticationPrincipal Authentication authentication ) {
	
	User user=userService.getCurrentlyLoggedInCustomer(authentication);
	List<CartItem> cartItems=cartServices.listCartItems(user);
	Product prod = new Product();
	Optional<Product> pro = proServ.getProduct(id);
	if(pro.isPresent()) {
		prod = pro.get();
		}
	CartItem cart = new CartItem(prod ,1);
	cartServices.addItemtoCart(cart);
	cartItems.add(cart);
	model.addAttribute("cartItems", cartItems);
	model.addAttribute("pageTitle", "Shopping Cart");
	return "redirect:/home";
}
@GetMapping("/cart_shopping")
public String ShowCart(Model model) {
	List<CartItem> cartItems = cartServices.getAllItems();
	double sum = 0;
	for (CartItem cart : cartItems) {
		 sum += cart.getProduct().getPrice();
	}
	model.addAttribute("cartItems", cartItems);
	model.addAttribute("pageTitle", "Shopping Cart");
	model.addAttribute("sum", sum);
	return "shopping_cart";
}
}
