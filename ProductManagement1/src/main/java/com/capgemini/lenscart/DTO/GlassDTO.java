package com.capgemini.lenscart.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GlassDTO {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "Name cannot be empty")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    private String image;

    @NotNull(message = "Brand cannot be empty")
    @Size(min = 1, message = "Brand name must be between 2 and 50 characters")
    private String brand;

    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private double price;

    @NotNull(message = "Type cannot be empty")
    private String type;

    @NotNull(message = "Power range cannot be empty")
    private String powerRange;

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
}
