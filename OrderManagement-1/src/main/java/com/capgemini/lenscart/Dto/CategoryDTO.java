package com.capgemini.lenscart.Dto;

public class CategoryDTO {

	private Long categoryId;

	
	private String categoryName;

	// Getters and Setters
	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	// Constructor with validation annotations
	public CategoryDTO(Long categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

	// Default constructor
	public CategoryDTO() {
	}
}