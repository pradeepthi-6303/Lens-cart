package com.capgemini.lenscart.DTO;



import com.capgemini.lenscart.entities.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class LensDTO {

    @NotNull(message = "{LensDTO.brand.notNull}")
    @NotBlank(message = "{LensDTO.brand.notBlank}")
    @Size(min = 2, max = 50, message = "{LensDTO.brand.size}")
    private String brand;

    @NotBlank(message = "{LensDTO.image.notBlank}")
    private String image;

    @NotNull(message = "{LensDTO.shape.notNull}")
    @NotBlank(message = "{LensDTO.shape.notBlank}")
    private String shape;

    @NotNull(message = "{LensDTO.color.notNull}")
    @NotBlank(message = "{LensDTO.color.notBlank}")
    private String color;

    @Positive(message = "{LensDTO.price.positive}")
    private double price;

    @NotNull(message = "{LensDTO.quantityInBox.notNull}")
    @Positive(message = "{LensDTO.quantityInBox.positive}")
    private int quantityInBox;
   // @NotNull(message = "CategoryId should not be Empty")
    private Category categoryId;

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
