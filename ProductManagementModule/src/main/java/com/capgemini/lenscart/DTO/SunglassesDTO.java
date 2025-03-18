package com.capgemini.lenscart.DTO;

import jakarta.validation.constraints.*;

public class SunglassesDTO {

    private Long id;

    @NotNull(message = "{SunglassesDTO.brand.notNull}")
    @Size(min = 2, max = 100, message = "{SunglassesDTO.brand.size}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{SunglassesDTO.brand.pattern}")
    private String brand;

    @NotNull(message = "{SunglassesDTO.name.notNull}")
    @Size(min = 2, max = 100, message = "{SunglassesDTO.name.size}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{SunglassesDTO.name.pattern}")
    private String name;

    private String image;

    @NotNull(message = "{SunglassesDTO.price.notNull}")
    @DecimalMin(value = "0.01", message = "{SunglassesDTO.price.min}")
//    @Pattern(regexp = "^\\d{1,3}(,\\d{3})*(\\.\\d{1,2})?$", message = "{SunglassesDTO.price.pattern}")
    private double price;

    @NotNull(message = "{SunglassesDTO.frameColor.notNull}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{SunglassesDTO.frameColor.pattern}")
    private String frameColor;

    @NotNull(message = "{SunglassesDTO.frameShape.notNull}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{SunglassesDTO.frameShape.pattern}")
    private String frameShape;

    @NotNull(message = "{SunglassesDTO.glassColor.notNull}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{SunglassesDTO.glassColor.pattern}")
    private String glassColor;

    @Min(value = 1, message = "{SunglassesDTO.weight.min}")
//    @Pattern(regexp = "^\\d{1,3}(,\\d{3})*(\\.\\d{1,2})?$", message = "{SunglassesDTO.weight.pattern}")
    private double weight;

    @NotNull(message = "{SunglassesDTO.categoryId.notNull}")
//    @Pattern(regexp = "^\\d{1,3}(,\\d{3})*(\\.\\d{1,2})?$", message = "{SunglassesDTO.categoryId.pattern}")
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    // Constructor

    public SunglassesDTO(Long id, String brand, String name, String image, double price, String frameColor,
                         String frameShape, String glassColor, double weight, Long categoryId) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.image = image;
        this.price = price;
        this.frameColor = frameColor;
        this.frameShape = frameShape;
        this.glassColor = glassColor;
        this.weight = weight;
        this.categoryId = categoryId;
    }

    // Default constructor (optional)
    public SunglassesDTO() {
    }
}