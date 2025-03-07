package com.capgemini.lenscart.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CartDto {
	    @NotNull(message = "Cart item name should not be empty")
	   @Size(min = 1, message = "Cart item name should not be empty")
	    private String name;

	    @NotNull(message = "Cart item brand should not be empty")
	    @Size(min = 1, message = "Cart item brand should not be empty")
	    private String brand;

	    @NotNull(message = "Cart item price should not be empty")
	    @Positive(message = "price must be positive")
	    
	    private double price;

	    @NotNull(message = "Cart image should not be empty")
	    @Size(min = 1, message = "Cart item image should not be empty")
	    private String image;

	    @NotNull(message = "Cart customer ID should not be empty")
	    private int customerId;
  

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}