package com.capgemini.lenscart.DTO;

import jakarta.validation.constraints.*;

public class GlassDTO {

    private Long id;

    @NotBlank(message = "{GlassDTO.name.notBlank}")
    @Size(min = 2, max = 100, message = "{GlassDTO.name.size}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{GlassDTO.name.pattern}")
    private String name;

    @NotNull(message="{GlassDTO.image.notnull}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{GlassDTO.image.pattern}")
    private String image;

    @NotBlank(message = "{GlassDTO.brand.notBlank}")
    @Size(min = 1, max = 10, message = "{GlassDTO.brand.size}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{GlassDTO.brand.pattern}")
    private String brand;

    @Min(value = 0, message = "{GlassDTO.price.min}")
    @NotNull(message = "{GlassDTO.price.notNull}")
    private Double price;

    @NotBlank(message = "{GlassDTO.type.notBlank}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{GlassDTO.type.pattern}")
    private String type;

    @NotBlank(message = "{GlassDTO.powerRange.notBlank}")
    private String powerRange;

    private Long categoryId;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public GlassDTO(Long id, String name, String image, String brand, Double price, String type, String powerRange, Long categoryId) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.brand = brand;
        this.price = price;
        this.type = type;
        this.powerRange = powerRange;
        this.categoryId = categoryId;
    }

    public GlassDTO() {
    }
}