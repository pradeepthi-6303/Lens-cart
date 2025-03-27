package com.capgemini.lenscart.Controller;

import com.capgemini.lenscart.Controllers.GlassController;
import com.capgemini.lenscart.DTO.GlassDTO;
import com.capgemini.lenscart.Service.GlassServiceImpl;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlassControllerTest {

    @Mock
    private GlassServiceImpl glassService;

    @InjectMocks
    private GlassController glassController;

    private GlassDTO glassDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        glassDTO = new GlassDTO(1L, "Test Glass", "image.jpg", "Brand X", 199.99, "Single Vision", "0-5", 2L);
    }

    @Test
    void testGetAllGlasses() {
        when(glassService.getAllGlasses()).thenReturn(List.of(glassDTO));

        var result = glassController.getAllGlasses();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Glass", result.get(0).getName());
    }

    @Test
    void testGetGlassById() throws ResourceNotFoundException {
        when(glassService.getGlassById(anyLong())).thenReturn(Optional.of(glassDTO));

        ResponseEntity<GlassDTO> response = glassController.getGlassById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Glass", response.getBody().getName());
    }

    @Test
    void testGetGlassById_NotFound() throws ResourceNotFoundException {
        when(glassService.getGlassById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<GlassDTO> response = glassController.getGlassById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testSaveGlass() {
        when(glassService.saveGlass(any(GlassDTO.class))).thenReturn(glassDTO);

        ResponseEntity<GlassDTO> response = glassController.saveGlass(glassDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Test Glass", response.getBody().getName());
    }

    @Test
    void testDeleteGlass() throws ResourceNotFoundException {
        doNothing().when(glassService).deleteGlass(anyLong());

        ResponseEntity<Void> response = glassController.deleteGlass(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
