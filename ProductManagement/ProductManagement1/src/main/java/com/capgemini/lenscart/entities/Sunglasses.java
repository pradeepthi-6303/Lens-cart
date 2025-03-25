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
	private Double price;
	private String frameColor;
	private String frameShape;
	private String glassColor;
	private Double weight;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	@NotNull(message = "Category cannot be null")
	private Category category; // Change to Category

	// Getters and Setters

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


}