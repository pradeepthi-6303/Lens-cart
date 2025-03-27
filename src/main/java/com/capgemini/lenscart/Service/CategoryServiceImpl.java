package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.lenscart.DTO.CategoryDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Repositories.CategoryRepo;
import com.capgemini.lenscart.entities.Category;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryRepo categoryRepository;

    // Convert Category entity to CategoryDTO
    private CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }

    // Convert CategoryDTO to Category entity
    private Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getCategoryName());  // Updated to categoryDTO.getName() instead of categoryDTO.getCategoryName()
        return category;
    }

    // Helper method to fetch category or throw exception
    private Category getCategoryOrThrow(Long id) throws ResourceNotFoundException {
        logger.debug("Attempting to fetch category with ID: {}", id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with ID " + id + " not found"));
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        logger.info("Fetching all categories from the database");
        List<Category> categories = categoryRepository.findAll();
        logger.info("Successfully fetched {} categories from the database", categories.size());
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) throws ResourceNotFoundException {
        logger.info("Fetching category with ID: {}", id);
        Category category = getCategoryOrThrow(id);
        logger.info("Successfully fetched category with ID: {}", id);
        return convertToDTO(category);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        logger.info("Creating a new category with name: {}", categoryDTO.getCategoryName());
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        logger.info("Successfully created category with ID: {}", savedCategory.getId());
        return convertToDTO(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) throws ResourceNotFoundException {
        logger.info("Updating category with ID: {}", id);
        Category existingCategory = getCategoryOrThrow(id);
        existingCategory.setName(categoryDTO.getCategoryName());  // Updated to categoryDTO.getName() instead of categoryDTO.getCategoryName()
        Category updatedCategory = categoryRepository.save(existingCategory);
        logger.info("Successfully updated category with ID: {}", id);
        return convertToDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) throws ResourceNotFoundException {
        logger.info("Attempting to delete category with ID: {}", id);
        Category category = getCategoryOrThrow(id);
        categoryRepository.delete(category);
        logger.info("Successfully deleted category with ID: {}", id);
    }
}