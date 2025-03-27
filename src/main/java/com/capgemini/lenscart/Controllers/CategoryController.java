package com.capgemini.lenscart.Controllers;

import com.capgemini.lenscart.DTO.CategoryDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        logger.info("Fetching all categories");
        List<CategoryDTO> categories = categoryService.getAllCategories();
        logger.info("Successfully fetched {} categories", categories.size());
        return categories;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Fetching category with ID: {}", id);
        try {
            CategoryDTO categoryDTO = categoryService.getCategoryById(id);
            logger.info("Successfully fetched category with ID: {}", id);
            return ResponseEntity.ok(categoryDTO);  // Return CategoryDTO directly with a 200 OK status
        } catch (ResourceNotFoundException e) {
            logger.error("Category with ID {} not found", id, e); // Log exception with stack trace
            throw e;  // Re-throw the exception after logging it
        }
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        logger.info("Creating a new category with name: {}", categoryDTO.getCategoryName());
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        logger.info("Successfully created category with ID: {}", savedCategory.getCategoryId());
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) throws ResourceNotFoundException {
        logger.info("Updating category with ID: {}", id);
        try {
            CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
            logger.info("Successfully updated category with ID: {}", id);
            return ResponseEntity.ok(updatedCategory);
        } catch (ResourceNotFoundException e) {
            logger.error("Category with ID {} not found", id, e); // Log exception with stack trace
            throw e;  // Re-throw the exception after logging it
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Attempting to delete category with ID: {}", id);
        try {
            categoryService.deleteCategory(id);
            logger.info("Successfully deleted category with ID: {}", id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            logger.error("Category with ID {} not found", id, e); // Log exception with stack trace
            throw e;  // Re-throw the exception after logging it
        }
    }
}