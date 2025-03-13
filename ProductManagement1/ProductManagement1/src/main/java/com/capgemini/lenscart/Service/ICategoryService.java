package com.capgemini.lenscart.Service;



import com.capgemini.lenscart.entities.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category createCategory(Category category);

    Category updateCategory(Long id, Category category);

    void deleteCategory(Long id);
}
