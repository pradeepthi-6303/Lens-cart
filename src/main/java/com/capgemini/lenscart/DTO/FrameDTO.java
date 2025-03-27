package com.capgemini.lenscart.DTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class FrameDTO {

    private Long id;

    @NotEmpty(message = "{FrameDTO.name.notEmpty}")
    @Size(min = 3, max = 100, message = "{FrameDTO.name.size}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{FrameDTO.name.pattern}")
    private String name;

    @NotEmpty(message = "{FrameDTO.color.notEmpty}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{FrameDTO.color.pattern}")
    private String color;

    @NotNull(message = "{FrameDTO.price.notNull}")
    @DecimalMin(value = "0.1", message = "{FrameDTO.price.decimalMin}")
    private Double price;

    @NotEmpty(message = "{FrameDTO.description.notNull}")
    @Size(max = 500, message = "{FrameDTO.description.size}")
//    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{FrameDTO.description.pattern}")
    private String description;

    @NotEmpty(message = "{FrameDTO.shape.notEmpty}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{FrameDTO.shape.pattern}")
    private String shape;

    @NotEmpty(message = "{FrameDTO.size.notEmpty}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{FrameDTO.size.pattern}")
    private String size;

    private Long categoryId;

    // Getters and setters...
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

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

    public FrameDTO(Long id, String name, String color, Double price, String description, String shape, String size, Long categoryId) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.description = description;
        this.shape = shape;
        this.size = size;
        this.categoryId = categoryId;
    }

    public FrameDTO() {
    }
}