package com.vente.dto;


import lombok.Data;

@Data
public class ProductDto {
	
	
	private long id;
	private String name;
	private int categoryId;
	private double price;
	private String description;
	private double weight;
	private String image;

}
