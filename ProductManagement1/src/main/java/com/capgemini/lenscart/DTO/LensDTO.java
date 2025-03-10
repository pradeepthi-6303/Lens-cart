package com.capgemini.lenscart.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class LensDTO {

    @NotNull(message = "Brand cannot be null")
    @NotBlank(message = "Brand is required")
    @Size(min = 2, max = 50, message = "Brand name must be between 2 and 50 characters")
    private String brand;

    @NotBlank(message = "Image URL cannot be blank")
    private String image;

    @NotNull(message = "Shape cannot be null")
    @NotBlank(message = "Shape is required")
    private String shape;

    @NotNull(message = "Color cannot be null")
    @NotBlank(message = "Color is required")
    private String color;

    @Positive(message = "Price must be positive")
    private double price;

    @NotNull(message = "Quantity in box cannot be null")
    @Positive(message = "Quantity in box must be positive")
    private int quantityInBox;

    // Getters and Setters

    public String getBrand() {
        return brand;
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
}
