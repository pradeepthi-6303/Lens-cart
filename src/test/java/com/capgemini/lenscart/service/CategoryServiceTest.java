package com.capgemini.lenscart.service;

import com.capgemini.lenscart.DTO.CategoryDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Repositories.CategoryRepo;
import com.capgemini.lenscart.Service.CategoryServiceImpl;
import com.capgemini.lenscart.entities.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryServiceTest {

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private Category category;
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks

        // Initialize category with values
        category = new Category();
        category.setId(1L);
        category.setName("Electronics");  // Ensure name is properly set

        categoryDTO = new CategoryDTO(1L, "Electronics");
    }

    // Test case for getAllCategories()
    @Test
    void getAllCategories_ShouldReturnCategoryDTOList() {
        // Arrange
        when(categoryRepo.findAll()).thenReturn(List.of(category));

        // Act
        List<CategoryDTO> categories = categoryService.getAllCategories();

        // Assert
        assertEquals(1, categories.size());
        assertEquals("Electronics", categories.get(0).getCategoryName());  // Ensure expected name
        verify(categoryRepo, times(1)).findAll();
    }

    // Test case for getCategoryById()
    @Test
    void getCategoryById_ExistingId_ShouldReturnCategoryDTO() throws ResourceNotFoundException {
        // Arrange
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));

        // Act
        CategoryDTO result = categoryService.getCategoryById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Electronics", result.getCategoryName());  // Ensure expected name
        verify(categoryRepo, times(1)).findById(1L);
    }

    @Test
    void getCategoryById_NonExistingId_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(categoryRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategoryById(1L));
        verify(categoryRepo, times(1)).findById(1L);
    }

    // Test case for createCategory()
    @Test
    void createCategory_ShouldReturnSavedCategoryDTO() {
        // Arrange
        when(categoryRepo.save(any(Category.class))).thenReturn(category);

        // Act
        CategoryDTO result = categoryService.createCategory(categoryDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Electronics", result.getCategoryName());  // Ensure expected name
        verify(categoryRepo, times(1)).save(any(Category.class));
    }

    // Test case for updateCategory()
    @Test
    void updateCategory_ExistingId_ShouldReturnUpdatedCategoryDTO() throws ResourceNotFoundException {
        // Arrange
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepo.save(any(Category.class))).thenReturn(category);

        // Act
        CategoryDTO updatedCategory = categoryService.updateCategory(1L, categoryDTO);

        // Assert
        assertNotNull(updatedCategory);
        assertEquals("Electronics", updatedCategory.getCategoryName());  // Ensure expected name
        verify(categoryRepo, times(1)).findById(1L);
        verify(categoryRepo, times(1)).save(any(Category.class));
    }

    @Test
    void updateCategory_NonExistingId_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(categoryRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> categoryService.updateCategory(1L, categoryDTO));
        verify(categoryRepo, times(1)).findById(1L);
    }

    // Test case for deleteCategory()
    @Test
    void deleteCategory_ExistingId_ShouldDeleteCategory() throws ResourceNotFoundException {
        // Arrange
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(category));

        // Act
        categoryService.deleteCategory(1L);

        // Assert
        verify(categoryRepo, times(1)).delete(any(Category.class));
    }

    @Test
    void deleteCategory_NonExistingId_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(categoryRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> categoryService.deleteCategory(1L));
        verify(categoryRepo, times(1)).findById(1L);
    }
}