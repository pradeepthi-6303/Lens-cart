package com.capgemini.lenscart.DTO;


import com.capgemini.lenscart.entities.Category;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SunglassesDTO {

    @NotNull(message = "{SunglassesDTO.brand.notNull}")
    @Size(min = 2, max = 100, message = "{SunglassesDTO.brand.size}")
    private String brand;

    @NotNull(message = "{SunglassesDTO.name.notNull}")
    @Size(min = 2, max = 100, message = "{SunglassesDTO.name.size}")
    private String name;

    private String image;

    @NotNull(message = "{SunglassesDTO.price.notNull}")
    @DecimalMin(value = "0.01", message = "{SunglassesDTO.price.min}")
    private double price;

    @NotNull(message = "{SunglassesDTO.frameColor.notNull}")
    private String frameColor;

    @NotNull(message = "{SunglassesDTO.frameShape.notNull}")
    private String frameShape;

    @NotNull(message = "{SunglassesDTO.glassColor.notNull}")
    private String glassColor;

    @Min(value = 1, message = "{SunglassesDTO.weight.min}")
    private double weight;
    @NotNull(message = "CategoryId should not be Empty")
    private Category categoryId;

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
