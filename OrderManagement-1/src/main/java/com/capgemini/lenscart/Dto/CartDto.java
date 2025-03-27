package com.capgemini.lenscart.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CartDto {

    @NotBlank(message = "{cart.name.notblank}")
    private String name;

    @NotBlank(message = "{cart.brand.notblank}")
    private String brand;	

    @NotNull(message = "{cart.price.notnull}")
    @Positive(message = "{cart.price.positive}")
    private Double price;

    @NotBlank(message = "{cart.image.notblank}")
    private String image;

    @NotNull(message = "{cart.customerId.notnull}")
    @Min(value = 1, message = "{cart.customerId.min}")
    private Integer customerId;

    
   
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
