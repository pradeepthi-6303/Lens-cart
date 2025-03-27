package com.capgemini.lenscart.Controllers;

import com.capgemini.lenscart.DTO.LensDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.LensServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lenses")
public class LensController {

    private static final Logger logger = LoggerFactory.getLogger(LensController.class); // Logger initialization

    @Autowired
    private LensServiceImpl lensService;

    @GetMapping("/all")
    public List<LensDTO> getAllLenses() {
        logger.info("Request received to fetch all lenses.");
        List<LensDTO> lenses = lensService.getAllLenses();
        logger.info("Successfully fetched {} lenses.", lenses.size());
        return lenses;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LensDTO> getLensById(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Request received to fetch lens with ID: {}", id);
        Optional<LensDTO> lensDTO = lensService.getLensById(id);
        if (lensDTO.isPresent()) {
            logger.info("Lens with ID {} successfully fetched.", id);
            return ResponseEntity.ok(lensDTO.get());
        } else {
            logger.error("Lens with ID {} not found.", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<LensDTO> saveLens(@Valid @RequestBody LensDTO lensDTO) {
        logger.info("Request received to save a new lens with brand: {}", lensDTO.getBrand());
        LensDTO savedLens = lensService.saveLens(lensDTO);
        logger.info("Lens with ID {} successfully saved.", savedLens.getId());
        return new ResponseEntity<>(savedLens, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLens(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Request received to delete lens with ID: {}", id);
        lensService.deleteLens(id);
        logger.info("Lens with ID {} successfully deleted.", id);
        return ResponseEntity.noContent().build();
    }
}