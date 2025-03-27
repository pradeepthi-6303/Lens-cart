package com.capgemini.lenscart.Controller;

import com.capgemini.lenscart.Controllers.FrameController;
import com.capgemini.lenscart.DTO.FrameDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.FrameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class FrameControllerTest {

    @Mock
    private FrameServiceImpl frameService;

    @InjectMocks
    private FrameController frameController;

    private MockMvc mockMvc;
    private FrameDTO frameDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
        mockMvc = MockMvcBuilders.standaloneSetup(frameController).build();

        frameDTO = new FrameDTO(1L, "Sunglasses", "Black", 299.99, "Stylish sunglasses", "Round", "Medium", 4L);
    }

    // Test case for getAllFrames
    @Test
    void getAllFrames_ShouldReturnListOfFrameDTO() throws Exception {
        // Arrange
        when(frameService.getAllFrames()).thenReturn(Arrays.asList(frameDTO));

        // Act & Assert
        mockMvc.perform(get("/api/frames"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Sunglasses"))
                .andExpect(jsonPath("$[0].color").value("Black"));
    }

    // Test case for getFrameById - frame exists
    @Test
    void getFrameById_ShouldReturnFrameDTO() throws Exception {
        // Arrange
        when(frameService.getFrameById(1L)).thenReturn(frameDTO);

        // Act & Assert
        mockMvc.perform(get("/api/frames/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sunglasses"))
                .andExpect(jsonPath("$.color").value("Black"));
    }



    // Test case for saveFrame
    @Test
    void saveFrame_ShouldReturnSavedFrameDTO() throws Exception {
        // Arrange
        when(frameService.saveFrame(any(FrameDTO.class))).thenReturn(frameDTO);

        // Act & Assert
        mockMvc.perform(post("/api/frames")
                        .contentType("application/json")
                        .content("{\"name\": \"Sunglasses\", \"color\": \"Black\", \"price\": 299.99, \"description\": \"Stylish sunglasses\", \"shape\": \"Round\", \"size\": \"Medium\", \"categoryId\": 4}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Sunglasses"))
                .andExpect(jsonPath("$.color").value("Black"));
    }

    // Test case for deleteFrame - frame exists
    @Test
    void deleteFrame_ShouldReturnNoContent() throws Exception {
        // Arrange
        doNothing().when(frameService).deleteFrame(1L);

        // Act & Assert
        mockMvc.perform(delete("/api/frames/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    // Test case for deleteFrame - frame not found

}
