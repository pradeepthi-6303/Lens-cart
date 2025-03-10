package com.capgemini.lenscart.entities;



import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

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

	public Sunglasses(Long id, String brand, String name, String image, double price, String frameColor,

			String frameShape, String glassColor, double weight) {

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

	}

	public Sunglasses() {

	}

 
}

 