package com.capgemini.lenscart.DTO;

import jakarta.validation.constraints.*;

public class LensDTO {

    private Long id;

    @NotNull(message = "{LensDTO.brand.notNull}")
    @NotBlank(message = "{LensDTO.brand.notBlank}")
    @Size(min = 2, max = 50, message = "{LensDTO.brand.size}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{LensDTO.brand.pattern}")
    private String brand;

    @NotBlank(message = "{LensDTO.image.notBlank}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{LensDTO.image.pattern}")
    private String image;

    @NotNull(message = "{LensDTO.shape.notNull}")
    @NotBlank(message = "{LensDTO.shape.notBlank}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{LensDTO.shape.pattern}")
    private String shape;

    @NotNull(message = "{LensDTO.color.notNull}")
    @NotBlank(message = "{LensDTO.color.notBlank}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{LensDTO.color.pattern}")
    private String color;

   @Positive(message = "{LensDTO.price.positive}")
    @NotNull(message = "{LensDTO.price.notNull}")
   private Double price;

    @NotNull(message = "{LensDTO.quantityInBox.notNull}")
    @Positive(message = "{LensDTO.quantityInBox.positive}")
    private int quantityInBox;

    private Long categoryId;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantityInBox() {
        return quantityInBox;
    }

    public void setQuantityInBox(int quantityInBox) {
        this.quantityInBox = quantityInBox;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    // Constructor

    public LensDTO(Long id, String brand, String image, String shape, String color, Double price,
                   int quantityInBox, Long categoryId) {
        this.id = id;
        this.brand = brand;
        this.image = image;
        this.shape = shape;
        this.color = color;
        this.price = price;
        this.quantityInBox = quantityInBox;
        this.categoryId = categoryId;
    }

    public LensDTO() {
    }
}