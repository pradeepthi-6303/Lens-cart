package com.capgemini.lenscart.Controller;

import com.capgemini.lenscart.Controllers.SunglassesController;
import com.capgemini.lenscart.DTO.SunglassesDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.ISunglassesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SunglassesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ISunglassesService sunglassesService;

    @InjectMocks
    private SunglassesController sunglassesController;

    private SunglassesDTO sunglassesDTO;

    @BeforeEach
    public void setUp() {
        // Initialize MockMvc and mock service
        mockMvc = MockMvcBuilders.standaloneSetup(sunglassesController).build();

        sunglassesDTO = new SunglassesDTO(1L, "Ray-Ban", "Wayfarer", "image.jpg", 150.00, "Black", "Square", "Gray", 150.0, 1L);
    }

    @Test
    public void testGetSunglassesById_Success() throws Exception {
        when(sunglassesService.getSunglassesById(1L)).thenReturn(Optional.of(sunglassesDTO));

        mockMvc.perform(get("/api/sunglasses/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand").value("Ray-Ban"));

        verify(sunglassesService, times(1)).getSunglassesById(1L);
    }

    @Test
    public void testGetSunglassesById_NotFound() throws Exception {
        when(sunglassesService.getSunglassesById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/sunglasses/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(sunglassesService, times(1)).getSunglassesById(1L);
    }

    @Test
    public void testSaveSunglasses() throws Exception {
        when(sunglassesService.saveSunglasses(any(SunglassesDTO.class))).thenReturn(sunglassesDTO);

        mockMvc.perform(post("/api/sunglasses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1, \"brand\":\"Ray-Ban\", \"name\":\"Wayfarer\", \"image\":\"image.jpg\", \"price\":150.00, \"frameColor\":\"Black\", \"frameShape\":\"Square\", \"glassColor\":\"Gray\", \"weight\":150.0, \"categoryId\":1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.brand").value("Ray-Ban"));

        verify(sunglassesService, times(1)).saveSunglasses(any(SunglassesDTO.class));
    }

    @Test
    public void testDeleteSunglasses_Success() throws Exception {
        doNothing().when(sunglassesService).deleteSunglasses(1L);

        mockMvc.perform(delete("/api/sunglasses/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(sunglassesService, times(1)).deleteSunglasses(1L);
    }

    @Test
    public void testDeleteSunglasses_NotFound() throws Exception {
        doThrow(new ResourceNotFoundException("Sunglasses not found")).when(sunglassesService).deleteSunglasses(1L);

        mockMvc.perform(delete("/api/sunglasses/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(sunglassesService, times(1)).deleteSunglasses(1L);
    }
}