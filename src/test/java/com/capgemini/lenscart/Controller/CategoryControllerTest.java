package com.capgemini.lenscart.Controller;

import com.capgemini.lenscart.Controllers.CategoryController;
import com.capgemini.lenscart.DTO.CategoryDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CategoryControllerTest {

    @Mock
    private CategoryServiceImpl categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;
    private CategoryDTO categoryDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();  // Set up MockMvc
        categoryDTO = new CategoryDTO(1L, "Electronics");
    }

    // Test case for getAllCategories()
    @Test
    void getAllCategories_ShouldReturnCategoryDTOList() throws Exception {
        // Arrange
        when(categoryService.getAllCategories()).thenReturn(List.of(categoryDTO));

        // Act & Assert
        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())  // 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].categoryId").value(1))
                .andExpect(jsonPath("$[0].categoryName").value("Electronics"));

        verify(categoryService, times(1)).getAllCategories();
    }

    // Test case for getCategoryById()
    @Test
    void getCategoryById_ExistingId_ShouldReturnCategoryDTO() throws Exception {
        // Arrange
        when(categoryService.getCategoryById(1L)).thenReturn(categoryDTO);

        // Act & Assert
        mockMvc.perform(get("/api/categories/{id}", 1L))
                .andExpect(status().isOk())  // 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.categoryId").value(1))
                .andExpect(jsonPath("$.categoryName").value("Electronics"));

        verify(categoryService, times(1)).getCategoryById(1L);
    }


    // Test case for createCategory()
    @Test
    void createCategory_ShouldReturnCreatedCategoryDTO() throws Exception {
        // Arrange
        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(categoryDTO);

        // Act & Assert
        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categoryDTO)))
                .andExpect(status().isCreated())  // 201 Created
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.categoryId").value(1))
                .andExpect(jsonPath("$.categoryName").value("Electronics"));

        verify(categoryService, times(1)).createCategory(any(CategoryDTO.class));
    }

    // Test case for updateCategory()
    @Test
    void updateCategory_ExistingId_ShouldReturnUpdatedCategoryDTO() throws Exception {
        // Arrange
        when(categoryService.updateCategory(eq(1L), any(CategoryDTO.class))).thenReturn(categoryDTO);

        // Act & Assert
        mockMvc.perform(put("/api/categories/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categoryDTO)))
                .andExpect(status().isOk())  // 200 OK
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.categoryId").value(1))
                .andExpect(jsonPath("$.categoryName").value("Electronics"));

        verify(categoryService, times(1)).updateCategory(eq(1L), any(CategoryDTO.class));
    }



    // Test case for deleteCategory()
    @Test
    void deleteCategory_ExistingId_ShouldReturnNoContent() throws Exception {
        // Arrange
        doNothing().when(categoryService).deleteCategory(1L);

        // Act & Assert
        mockMvc.perform(delete("/api/categories/{id}", 1L))
                .andExpect(status().isNoContent());  // 204 No Content

        verify(categoryService, times(1)).deleteCategory(1L);
    }
}