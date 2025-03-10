package com.capgemini.lenscart.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Lens {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String image;
    private String shape;
    private String color;
    private double price;
    private int quantityInBox;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantityInBox() {
		return quantityInBox;
	}
	public void setQuantityInBox(int quantityInBox) {
		this.quantityInBox = quantityInBox;
	}
	public Lens(Long id, String brand, String image, String shape, String color, double price, int quantityInBox) {
		super();
		this.id = id;
		this.brand = brand;
		this.image = image;
		this.shape = shape;
		this.color = color;
		this.price = price;
		this.quantityInBox = quantityInBox;
	}
	public Lens() {
	}
	public String getBrand() {
		// TODO Auto-generated method stub
		return brand;
	}
 
}