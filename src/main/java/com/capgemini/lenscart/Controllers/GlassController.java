package com.capgemini.lenscart.Controllers;

import com.capgemini.lenscart.DTO.GlassDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.GlassServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/glasses")
public class GlassController {

    private static final Logger logger = LoggerFactory.getLogger(GlassController.class); // Logger initialization

    @Autowired
    private GlassServiceImpl glassService;

    // Get all glasses
    @GetMapping
    public List<GlassDTO> getAllGlasses() {
        logger.info("Fetching all glasses.");
        List<GlassDTO> glasses = glassService.getAllGlasses();
        logger.info("Successfully fetched {} glasses.", glasses.size());
        return glasses;
    }

    // Get glass by ID
    @GetMapping("/{id}")
    public ResponseEntity<GlassDTO> getGlassById(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Fetching glass with ID: {}", id);
        return glassService.getGlassById(id)
                .map(glassDTO -> {
                    logger.info("Successfully fetched glass with ID: {}", id);
                    return ResponseEntity.ok(glassDTO);
                })
                .orElseGet(() -> {
                    logger.error("Glass with ID {} not found.", id);
                    return ResponseEntity.notFound().build();
                });
    }

    // Save new glass
    @PostMapping
    public ResponseEntity<GlassDTO> saveGlass(@RequestBody @Valid GlassDTO glassDTO) {
        logger.info("Saving new glass with name: {}", glassDTO.getName());
        GlassDTO savedGlass = glassService.saveGlass(glassDTO);
        logger.info("Successfully saved glass with ID: {}", savedGlass.getId());
        return new ResponseEntity<>(savedGlass, HttpStatus.CREATED);
    }

    // Delete glass by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGlass(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Attempting to delete glass with ID: {}", id);
        glassService.deleteGlass(id);
        logger.info("Successfully deleted glass with ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}