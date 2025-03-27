package com.capgemini.lenscart.service;

import com.capgemini.lenscart.DTO.GlassDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.GlassServiceImpl;
import com.capgemini.lenscart.entities.Glass;
import com.capgemini.lenscart.entities.Category;
import com.capgemini.lenscart.Repositories.GlassRepo;
import com.capgemini.lenscart.Repositories.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class GlassServiceTest {

    @Mock
    private GlassRepo glassRepo;

    @Mock
    private CategoryRepo categoryRepo;


    @InjectMocks
    private GlassServiceImpl glassService;

    private GlassDTO glassDTO;
    private Glass glass;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize DTO and Entity
        glassDTO = new GlassDTO(1L, "Blue Light Glasses", "image_url", "Brand A", 199.99, "Blue Light", "2-4", 2L);

        glass = new Glass();
        glass.setId(1L);
        glass.setName("Blue Light Glasses");
        glass.setImage("image_url");
        glass.setBrand("Brand A");
        glass.setPrice(199.99);
        glass.setType("Blue Light");
        glass.setPowerRange("2-4");

        Category category = new Category();
        category.setId(2L);
        glass.setCategory(category);
    }

    // Test case for getAllGlasses
    @Test
    void getAllGlasses_ShouldReturnListOfGlassDTO() {
        // Arrange
        when(glassRepo.findAll()).thenReturn(Arrays.asList(glass));

        // Act
        var result = glassService.getAllGlasses();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Blue Light Glasses", result.get(0).getName());
    }

    // Test case for getGlassById when glass exists
    @Test
    void getGlassById_ShouldReturnGlassDTO() {
        // Arrange
        when(glassRepo.findById(1L)).thenReturn(Optional.of(glass));

        // Act
        var result = glassService.getGlassById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Blue Light Glasses", result.get().getName());
    }

    // Test case for getGlassById when glass does not exist
    @Test
    void getGlassById_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(glassRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> glassService.getGlassById(1L));
        assertEquals("Glass with ID 1 not found", exception.getMessage());
    }

    // Test case for saveGlass
    @Test
    void saveGlass_ShouldReturnSavedGlassDTO() {
        // Arrange
        when(categoryRepo.findById(2L)).thenReturn(Optional.of(new Category())); // Mock category
        when(glassRepo.save(any(Glass.class))).thenReturn(glass);

        // Act
        GlassDTO result = glassService.saveGlass(glassDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Blue Light Glasses", result.getName());
        verify(glassRepo, times(1)).save(any(Glass.class)); // Verify if save was called once
    }

    // Test case for deleteGlass when glass exists
    @Test
    void deleteGlass_ShouldDeleteGlass() {
        // Arrange
        when(glassRepo.findById(1L)).thenReturn(Optional.of(glass));

        // Act
        glassService.deleteGlass(1L);

        // Assert
        verify(glassRepo, times(1)).delete(glass); // Verify if delete was called once
    }

    // Test case for deleteGlass when glass does not exist
    @Test
    void deleteGlass_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(glassRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> glassService.deleteGlass(1L));
        assertEquals("Glass with ID 1 not found", exception.getMessage());
    }
}