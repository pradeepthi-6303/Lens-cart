package com.capgemini.lenscart.Controllers;

import com.capgemini.lenscart.DTO.SunglassesDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.SunglassesServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/sunglasses")
public class SunglassesController {

    private static final Logger logger = LoggerFactory.getLogger(SunglassesController.class); // Logger initialization

    @Autowired
    private SunglassesServiceImpl sunglassesService;

    @GetMapping("/all")
    public List<SunglassesDTO> getAllSunglasses() {
        logger.info("Received request to fetch all sunglasses.");
        List<SunglassesDTO> sunglassesList = sunglassesService.getAllSunglasses();
        logger.info("Successfully fetched {} sunglasses.", sunglassesList.size());
        return sunglassesList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<SunglassesDTO> getSunglassesById(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Received request to fetch sunglasses with ID: {}", id);
        return sunglassesService.getSunglassesById(id)
                .map(sunglassesDTO -> {
                    logger.info("Successfully fetched sunglasses with ID: {}", id);
                    return ResponseEntity.ok(sunglassesDTO);
                })
                .orElseGet(() -> {
                    logger.error("Sunglasses with ID {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping()
    public ResponseEntity<SunglassesDTO> saveSunglasses(@Valid @RequestBody SunglassesDTO sunglassesDTO) {
        logger.info("Received request to save a new pair of sunglasses with brand: {}", sunglassesDTO.getBrand());
        SunglassesDTO savedSunglasses = sunglassesService.saveSunglasses(sunglassesDTO);
        logger.info("Successfully saved sunglasses with ID: {}", savedSunglasses.getId());
        return new ResponseEntity<>(savedSunglasses, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSunglasses(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Received request to delete sunglasses with ID: {}", id);
        try {
            sunglassesService.deleteSunglasses(id);
            logger.info("Successfully deleted sunglasses with ID: {}", id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            logger.error("Sunglasses with ID {} not found for deletion", id);
            throw e; // Re-throwing the exception to be handled elsewhere, maybe by a global exception handler
        }
    }
}