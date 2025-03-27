package com.capgemini.lenscart.service;

import com.capgemini.lenscart.DTO.SunglassesDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.SunglassesServiceImpl;
import com.capgemini.lenscart.entities.Sunglasses;
import com.capgemini.lenscart.entities.Category;
import com.capgemini.lenscart.Repositories.SunglassesRepo;
import com.capgemini.lenscart.Repositories.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SunglassesServiceTest {

    @Mock
    private SunglassesRepo sunglassesRepo;

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private SunglassesServiceImpl sunglassesService;

    private SunglassesDTO sunglassesDTO;
    private Sunglasses sunglasses;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize DTO and Entity
        sunglassesDTO = new SunglassesDTO(1L, "Ray-Ban", "Aviator", "image_url", 150.00, "Gold", "Aviator", "Green", 30.0, 2L);

        sunglasses = new Sunglasses();
        sunglasses.setId(1L);
        sunglasses.setBrand("Ray-Ban");
        sunglasses.setName("Aviator");
        sunglasses.setImage("image_url");
        sunglasses.setPrice(150.00);
        sunglasses.setFrameColor("Gold");
        sunglasses.setFrameShape("Aviator");
        sunglasses.setGlassColor("Green");
        sunglasses.setWeight(30.0);

        Category category = new Category();
        category.setId(2L);
        sunglasses.setCategory(category);
    }

    // Test case for getAllSunglasses
    @Test
    void getAllSunglasses_ShouldReturnListOfSunglassesDTO() {
        // Arrange
        when(sunglassesRepo.findAll()).thenReturn(Arrays.asList(sunglasses));

        // Act
        var result = sunglassesService.getAllSunglasses();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Aviator", result.get(0).getName());
    }

    // Test case for getSunglassesById when sunglasses exist
    @Test
    void getSunglassesById_ShouldReturnSunglassesDTO() {
        // Arrange
        when(sunglassesRepo.findById(1L)).thenReturn(Optional.of(sunglasses));

        // Act
        var result = sunglassesService.getSunglassesById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Aviator", result.get().getName());
    }

    // Test case for getSunglassesById when sunglasses do not exist


    // Test case for saveSunglasses
    @Test
    void saveSunglasses_ShouldReturnSavedSunglassesDTO() {
        // Arrange
        when(categoryRepo.findById(2L)).thenReturn(Optional.of(new Category())); // Mock category
        when(sunglassesRepo.save(any(Sunglasses.class))).thenReturn(sunglasses);

        // Act
        SunglassesDTO result = sunglassesService.saveSunglasses(sunglassesDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Aviator", result.getName());
        verify(sunglassesRepo, times(1)).save(any(Sunglasses.class)); // Verify if save was called once
    }

    // Test case for deleteSunglasses when sunglasses exist
    @Test
    void deleteSunglasses_ShouldDeleteSunglasses() {
        // Arrange
        when(sunglassesRepo.findById(1L)).thenReturn(Optional.of(sunglasses));

        // Act
        sunglassesService.deleteSunglasses(1L);

        // Assert
        verify(sunglassesRepo, times(1)).delete(sunglasses); // Verify if delete was called once
    }

    // Test case for deleteSunglasses when sunglasses do not exist
    @Test
    void deleteSunglasses_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(sunglassesRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> sunglassesService.deleteSunglasses(1L));
        assertEquals("Sunglasses with ID 1 not found", exception.getMessage());
    }
}
