package com.capgemini.lenscart.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SunglassesDTO {

    @NotNull(message = "Brand cannot be null")
    @Size(min = 2, max = 100, message = "Brand name should be between 2 and 100 characters")
    private String brand;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 100, message = "Sunglasses name should be between 2 and 100 characters")
    private String name;

    private String image;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.01", message = "Price should be greater than 0")
    private double price;

    @NotNull(message = "Frame color cannot be null")
    private String frameColor;

    @NotNull(message = "Frame shape cannot be null")
    private String frameShape;

    @NotNull(message = "Glass color cannot be null")
    private String glassColor;

    @Min(value = 1, message = "Weight must be greater than 0")
    private double weight;

    // Getters and Setters

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
}
