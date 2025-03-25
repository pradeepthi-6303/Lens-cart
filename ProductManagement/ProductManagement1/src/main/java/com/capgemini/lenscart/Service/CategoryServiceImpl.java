package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenscart.DTO.CategoryDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Repositories.CategoryRepo;
import com.capgemini.lenscart.entities.Category;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepo categoryRepository;

    // Convert Category entity to CategoryDTO
    private CategoryDTO convertToDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }

    // Convert CategoryDTO to Category entity
    private Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getCategoryName());
        return category;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                         .map(this::convertToDTO)
                         .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) throws ResourceNotFoundException{
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with ID " + id + " not found"));
        return convertToDTO(category);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return convertToDTO(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) throws ResourceNotFoundException {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with ID " + id + " not found"));

        existingCategory.setName(categoryDTO.getCategoryName());
        Category updatedCategory = categoryRepository.save(existingCategory);

        return convertToDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Long id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with ID " + id + " not found"));
        categoryRepository.delete(category);
    }
}
