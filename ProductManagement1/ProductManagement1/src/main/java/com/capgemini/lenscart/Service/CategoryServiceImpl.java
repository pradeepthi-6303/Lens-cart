package com.capgemini.lenscart.Service;


import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Repositories.CategoryRepo;
import com.capgemini.lenscart.entities.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

	 @Autowired
	    private CategoryRepo categoryRepository;

	    @Override
	    public List<Category> getAllCategories() {
	        return categoryRepository.findAll();
	    }

	    @Override
	    public Category getCategoryById(Long id) {
	        // Instead of returning Optional, throw an exception if not found
	        return categoryRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
	    }

	    @Override
	    public Category createCategory(Category category) {
	        // Save and return the newly created category
	        return categoryRepository.save(category);
	    }

	    @Override
	    public Category updateCategory(Long id, Category category) {
	        // Check if the category exists
	        Category existingCategory = categoryRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));

	        // Update the existing category fields (Fixing the field name)
	        existingCategory.setCategoryName(category.getCategoryName());  // Corrected field name

	        // Save and return the updated category
	        return categoryRepository.save(existingCategory);
	    }

	    @Override
	    public void deleteCategory(Long id) {
	        // Check if the category exists before deleting
	        if (!categoryRepository.existsById(id)) {
	            throw new ResourceNotFoundException("Category with id " + id + " not found");
	        }

	        // Delete the category
	        categoryRepository.deleteById(id);
	    }

}
