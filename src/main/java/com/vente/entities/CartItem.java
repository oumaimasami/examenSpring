package com.vente.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="cart_items")
public class CartItem {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private Integer id;

@ManyToOne
@JoinColumn(name="product_id")
private Product product;

public CartItem(Product product, int quantity) {
	super();
	this.product = product;
	this.quantity = quantity;
}

@ManyToOne
@JoinColumn(name="user_id")
private User user;

private int quantity;
}
