package com.capgemini.lenscart.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Glass {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private String brand;
    private double price;
    private String type; // zero power, digital screen protection, single vision, bifocal powered glass
    private String powerRange;
    // +/-10, +/-8, +/-4
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
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
	public Glass(Long id, String name, String image, String brand, double price, String type, String powerRange) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.brand = brand;
		this.price = price;
		this.type = type;
		this.powerRange = powerRange;
	}
	public Glass() {
	}

 
}