package com.capgemini.lenscart.Controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.lenscart.DTO.GlassDTO;
import com.capgemini.lenscart.Service.IGlassService;
import com.capgemini.lenscart.entities.Glass;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/glasses")
public class GlassController {

    @Autowired
    private IGlassService glassService;

    @GetMapping("/all")
    public List<Glass> getAllGlasses() {
        return glassService.getAllGlasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Glass> getGlassById(@PathVariable Long id) {
        Optional<Glass> glass = glassService.getGlassById(id);
        return glass.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{createGlass}")
    public ResponseEntity<Glass> createGlass(@Valid @RequestBody GlassDTO glassDTO) {
        // Convert DTO to entity
        Glass glass = new Glass();
        glass.setId(glassDTO.getId());
        glass.setName(glassDTO.getName());
        glass.setImage(glassDTO.getImage());
        glass.setBrand(glassDTO.getBrand());
        glass.setPrice(glassDTO.getPrice());
        glass.setType(glassDTO.getType());
        glass.setPowerRange(glassDTO.getPowerRange());

        Glass savedGlass = glassService.saveGlass(glass);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGlass);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Glass> updateGlass(@PathVariable Long id, @RequestBody Glass glassDetails) {
        Optional<Glass> glass = glassService.getGlassById(id);
        if (glass.isPresent()) {
            Glass updatedGlass = glass.get();
            updatedGlass.setName(glassDetails.getName());
            updatedGlass.setImage(glassDetails.getImage());
            updatedGlass.setBrand(glassDetails.getBrand());
            updatedGlass.setPrice(glassDetails.getPrice());
            updatedGlass.setType(glassDetails.getType());
            updatedGlass.setPowerRange(glassDetails.getPowerRange());
            return ResponseEntity.ok(glassService.saveGlass(updatedGlass));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGlass(@PathVariable Long id) {
        glassService.deleteGlass(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
