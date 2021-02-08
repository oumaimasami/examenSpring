package com.vente.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;


@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long product_id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	private double price;
	private String description;
	private double weight;
	private String image;


}
