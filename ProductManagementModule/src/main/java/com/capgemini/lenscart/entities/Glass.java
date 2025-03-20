package com.capgemini.lenscart.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Glass {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String image;
	private String brand;
	private Double price;
	private String type; // zero power, digital screen protection, single vision, bifocal powered glass
	private String powerRange;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	@NotNull(message = "Category cannot be null")
	private Category category;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPowerRange() {
		return powerRange;
	}

	public void setPowerRange(String powerRange) {
		this.powerRange = powerRange;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	// Constructors
	public Glass(Long id, String name, String image, String brand, Double price, String type, String powerRange, Category category) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.brand = brand;
		this.price = price;
		this.type = type;
		this.powerRange = powerRange;
		this.category = category;
	}

	public Glass() {
	}
}
