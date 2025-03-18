package com.capgemini.lenscart.Service;

import java.util.List;

import com.capgemini.lenscart.DTO.CategoryDTO;

public interface ICategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryById(Long id);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);

    void deleteCategory(Long id);

}
