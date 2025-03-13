package com.capgemini.lenscart.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity

public class Sunglasses {

	@Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String brand;

    private String name;

    private String image;

    private double price;

    private String frameColor;

    private String frameShape;

    private String glassColor;

    private double weight;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    @NotNull(message = "Category cannot be null") 
    private Category categoryId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getFrameColor() {
		return frameColor;
	}
	public void setFrameColor(String frameColor) {
		this.frameColor = frameColor;
	}
	public String getFrameShape() {
		return frameShape;
	}
	public void setFrameShape(String frameShape) {
		this.frameShape = frameShape;
	}
	public String getGlassColor() {
		return glassColor;
	}
	public void setGlassColor(String glassColor) {
		this.glassColor = glassColor;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Category getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}
	public Sunglasses(Long id, String brand, String name, String image, double price, String frameColor,
			String frameShape, String glassColor, double weight, Category categoryId) {
		super();
		this.id = id;
		this.brand = brand;
		this.name = name;
		this.image = image;
		this.price = price;
		this.frameColor = frameColor;
		this.frameShape = frameShape;
		this.glassColor = glassColor;
		this.weight = weight;
		this.categoryId = categoryId;
	}
	public Sunglasses() {
		super();
		// TODO Auto-generated constructor stub
	}
    
 
}

 