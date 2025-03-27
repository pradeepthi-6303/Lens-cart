package com.capgemini.lenscart.service;


import com.capgemini.lenscart.DTO.FrameDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.FrameServiceImpl;
import com.capgemini.lenscart.entities.Frame;
import com.capgemini.lenscart.entities.Category;
import com.capgemini.lenscart.Repositories.FrameRepo;
import com.capgemini.lenscart.Repositories.CategoryRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FrameServiceTest {

    @Mock
    private FrameRepo frameRepo;

    @Mock
    private CategoryRepo categoryRepo;

    @InjectMocks
    private FrameServiceImpl frameService;

    private FrameDTO frameDTO;
    private Frame frame;
    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks

        category = new Category();
        category.setId(4L);  // Default category ID

        frameDTO = new FrameDTO(1L, "Sunglasses", "Black", 299.99, "Stylish sunglasses", "Round", "Medium", 4L);

        frame = new Frame();
        frame.setId(1L);
        frame.setName("Sunglasses");
        frame.setColor("Black");
        frame.setPrice(299.99);
        frame.setDescription("Stylish sunglasses");
        frame.setShape("Round");
        frame.setSize("Medium");
        frame.setCategory(category);
    }

    // Test case for getAllFrames
    @Test
    void getAllFrames_ShouldReturnListOfFrameDTO() {
        // Arrange
        when(frameRepo.findAll()).thenReturn(List.of(frame));

        // Act
        var result = frameService.getAllFrames();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Sunglasses", result.get(0).getName());
        verify(frameRepo, times(1)).findAll();
    }

    // Test case for getFrameById - frame exists
    @Test
    void getFrameById_ShouldReturnFrameDTO() throws ResourceNotFoundException {
        // Arrange
        when(frameRepo.findById(1L)).thenReturn(Optional.of(frame));

        // Act
        FrameDTO result = frameService.getFrameById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Sunglasses", result.getName());
        assertEquals(1L, result.getId());
        verify(frameRepo, times(1)).findById(1L);
    }

    // Test case for getFrameById - frame does not exist
    @Test
    void getFrameById_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(frameRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            frameService.getFrameById(1L);
        });
        assertEquals("Frame with ID 1 not found", exception.getMessage());
        verify(frameRepo, times(1)).findById(1L);
    }

    // Test case for saveFrame
    @Test
    void saveFrame_ShouldReturnSavedFrameDTO() {
        // Arrange
        when(categoryRepo.findById(4L)).thenReturn(Optional.of(category));
        when(frameRepo.save(any(Frame.class))).thenReturn(frame);

        // Act
        FrameDTO result = frameService.saveFrame(frameDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Sunglasses", result.getName());
        verify(categoryRepo, times(1)).findById(4L);
        verify(frameRepo, times(1)).save(any(Frame.class));
    }

    // Test case for saveFrame - category not found
    @Test
    void saveFrame_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(categoryRepo.findById(4L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            frameService.saveFrame(frameDTO);
        });
        assertEquals("Category not found", exception.getMessage());
        verify(categoryRepo, times(1)).findById(4L);
        verify(frameRepo, times(0)).save(any(Frame.class));
    }

    // Test case for deleteFrame - frame exists
    @Test
    void deleteFrame_ShouldDeleteFrame() throws ResourceNotFoundException {
        // Arrange
        when(frameRepo.findById(1L)).thenReturn(Optional.of(frame));
        doNothing().when(frameRepo).delete(frame);

        // Act
        frameService.deleteFrame(1L);

        // Assert
        verify(frameRepo, times(1)).findById(1L);
        verify(frameRepo, times(1)).delete(frame);
    }

    // Test case for deleteFrame - frame does not exist
    @Test
    void deleteFrame_ShouldThrowResourceNotFoundException() {
        // Arrange
        when(frameRepo.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            frameService.deleteFrame(1L);
        });
        assertEquals("Frame with ID 1 not found", exception.getMessage());
        verify(frameRepo, times(1)).findById(1L);
        verify(frameRepo, times(0)).delete(any(Frame.class));
    }
}