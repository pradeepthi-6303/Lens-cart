package com.capgemini.lenscart.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FrameDTO {

    private Long id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 3, max = 100, message = "Name should be between 3 and 100 characters")
    private String name;

    @NotEmpty(message = "Color must not be empty")
    private String color;

    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.1", message = "Price must be greater than 0")
    private Double price;
    @NotNull(message = "Description is required")

    @Size(max = 500, message = "Description should not exceed 500 characters")
    private String description;

    @NotEmpty(message = "Shape must not be empty")
    private String shape;

    @NotEmpty(message = "Size must not be empty")
    private String size;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
