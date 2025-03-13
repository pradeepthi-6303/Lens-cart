package com.capgemini.lenscart.DTO;


import com.capgemini.lenscart.entities.Category;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class GlassDTO {

    @NotNull(message = "{GlassDTO.id.notNull}")
    private Long id;

    @NotNull(message = "{GlassDTO.name.notNull}")
    @Size(min = 2, max = 100, message = "{GlassDTO.name.size}")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Quota must contain only letters")
    
    private String name;

    private String image;

    @NotNull(message = "{GlassDTO.brand.notNull}")
    @Size(min = 1, max = 50, message = "{GlassDTO.brand.size}")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Quota must contain only letters")
    private String brand;

    @Min(value = 0, message = "{GlassDTO.price.min}")
    private double price;

    @NotNull(message = "{GlassDTO.type.notNull}")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Quota must contain only letters")
    private String type;

    @NotNull(message = "{GlassDTO.powerRange.notNull}")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Quota must contain only letters")
    private String powerRange;
    @NotNull(message = "CategoryId should not be Empty")
   
    private Category categoryId;
    

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
