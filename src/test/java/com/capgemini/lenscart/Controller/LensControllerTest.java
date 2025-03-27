package com.capgemini.lenscart.Controller;

import com.capgemini.lenscart.Controllers.LensController;
import com.capgemini.lenscart.DTO.LensDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.LensServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class LensControllerTest {

    @Mock
    private LensServiceImpl lensService;

    @InjectMocks
    private LensController lensController;

    private LensDTO lensDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize DTO
        lensDTO = new LensDTO(1L, "Brand A", "image_url", "Round", "Blue", 99.99, 10, 1L);
    }

    // Test case for getAllLenses


    // Test case for getLensById when lens exists
    @Test
    void getLensById_ShouldReturnLensDTO() throws ResourceNotFoundException {
        // Arrange
        when(lensService.getLensById(1L)).thenReturn(Optional.of(lensDTO));

        // Act
        ResponseEntity<LensDTO> response = lensController.getLensById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Brand A", response.getBody().getBrand());
    }

    // Test case for getLensById when lens does not exist
    @Test
    void getLensById_ShouldReturnNotFound() throws ResourceNotFoundException {
        // Arrange
        when(lensService.getLensById(1L)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<LensDTO> response = lensController.getLensById(1L);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // Test case for saveLens
    @Test
    void saveLens_ShouldReturnSavedLensDTO() {
        // Arrange
        when(lensService.saveLens(lensDTO)).thenReturn(lensDTO);

        // Act
        ResponseEntity<LensDTO> response = lensController.saveLens(lensDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Brand A", response.getBody().getBrand());
        assertEquals(1L, response.getBody().getId());
    }

    // Test case for deleteLens when lens exists
    @Test
    void deleteLens_ShouldDeleteLens() throws ResourceNotFoundException {
        // Arrange
        doNothing().when(lensService).deleteLens(1L);

        // Act
        ResponseEntity<Void> response = lensController.deleteLens(1L);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(lensService, times(1)).deleteLens(1L); // Verify if delete method was called once
    }

    // Test case for deleteLens when lens does not exist
    @Test
    void deleteLens_ShouldReturnNotFound() throws ResourceNotFoundException {
        // Arrange
        doThrow(new ResourceNotFoundException("Lens with ID 1 not found")).when(lensService).deleteLens(1L);

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> lensController.deleteLens(1L));
        assertEquals("Lens with ID 1 not found", exception.getMessage());
    }
}