package com.capgemini.lenscart.DTO;



import com.capgemini.lenscart.entities.Category;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class FrameDTO {

    private Long id;

    @NotEmpty(message = "{FrameDTO.name.notEmpty}")
    @Size(min = 3, max = 100, message = "{FrameDTO.name.size}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Quota must contain only letters")
    private String name;

    @NotEmpty(message = "{FrameDTO.color.notEmpty}")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Quota must contain only letters")
    private String color;

    @NotNull(message = "{FrameDTO.price.notNull}")
    @DecimalMin(value = "0.1", message = "{FrameDTO.price.decimalMin}")
    private Double price;

    @NotNull(message = "{FrameDTO.description.notNull}")
    @Size(max = 500, message = "{FrameDTO.description.size}")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Quota must contain only letters")
    private String description;

    @NotEmpty(message = "{FrameDTO.shape.notEmpty}")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Quota must contain only letters")
    private String shape;

    @NotEmpty(message = "{FrameDTO.size.notEmpty}")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Quota must contain only letters")
    private String size;

    @NotNull(message = "Category cannot be null") 
    private Category categoryId;
    
 
    

    public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

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
