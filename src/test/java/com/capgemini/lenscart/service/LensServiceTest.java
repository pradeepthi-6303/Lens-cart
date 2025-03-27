package com.capgemini.lenscart.service;

import com.capgemini.lenscart.DTO.LensDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.LensServiceImpl;
import com.capgemini.lenscart.entities.Lens;
import com.capgemini.lenscart.entities.Category;
import com.capgemini.lenscart.Repositories.LensRepo;
import com.capgemini.lenscart.Repositories.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LensServiceTest {

    @Mock
    private LensRepo lensRepo;

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private LensServiceImpl lensService;

    private LensDTO lensDTO;
    private Lens lens;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize DTO and Entity
        lensDTO = new LensDTO(1L, "Brand A", "image_url", "Round", "Blue", 99.99, 10, 1L);

        lens = new Lens();
        lens.setId(1L);
        lens.setBrand("Brand A");
        lens.setImage("image_url");
        lens.setShape("Round");
        lens.setColor("Blue");
        lens.setPrice(99.99);
        lens.setQuantityInBox(10);

        Category category = new Category();
        category.setId(1L);
        lens.setCategory(category);
    }

    // Test case for getAllLenses
    @Test
    void getAllLenses_ShouldReturnListOfLensDTO() {
        // Arrange
        when(lensRepo.findAll()).thenReturn(Arrays.asList(lens));

        // Act
        var result = lensService.getAllLenses();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Brand A", result.get(0).getBrand());
    }

    // Test case for getLensById when lens exists
    @Test
    void getLensById_ShouldReturnLensDTO() {
        // Arrange
        when(lensRepo.findById(1L)).thenReturn(Optional.of(lens));

        // Act
        var result = lensService.getLensById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Brand A", result.get().getBrand());
    }

    // Test case for getLensById when lens does not exist
    @Test
    void getLensById_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(lensRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> lensService.getLensById(1L));
        assertEquals("Lens with ID 1 not found", exception.getMessage());
    }

    // Test case for saveLens
    @Test
    void saveLens_ShouldReturnSavedLensDTO() {
        // Arrange
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(new Category())); // Mock category
        when(lensRepo.save(any(Lens.class))).thenReturn(lens);

        // Act
        LensDTO result = lensService.saveLens(lensDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Brand A", result.getBrand());
        verify(lensRepo, times(1)).save(any(Lens.class)); // Verify if save was called once
    }

    // Test case for deleteLens when lens exists
    @Test
    void deleteLens_ShouldDeleteLens() {
        // Arrange
        when(lensRepo.findById(1L)).thenReturn(Optional.of(lens));

        // Act
        lensService.deleteLens(1L);

        // Assert
        verify(lensRepo, times(1)).delete(lens); // Verify if delete was called once
    }

    // Test case for deleteLens when lens does not exist
    @Test
    void deleteLens_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(lensRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> lensService.deleteLens(1L));
        assertEquals("Lens with ID 1 not found", exception.getMessage());
    }
}