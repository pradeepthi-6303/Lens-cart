package com.capgemini.lenscart.Controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.lenscart.DTO.SunglassesDTO;
import com.capgemini.lenscart.Service.ISunglassesService;
import com.capgemini.lenscart.entities.Sunglasses;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/sunglasses")
public class SunglassesController {

    @Autowired
    private ISunglassesService sunglassesService;

    @GetMapping("/all")
    public List<Sunglasses> getAllSunglasses() {
        return sunglassesService.getAllSunglasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sunglasses> getSunglassesById(@PathVariable Long id) {
        Optional<Sunglasses> sunglasses = sunglassesService.getSunglassesById(id);
        return sunglasses.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{createglasses}")
  
    	public ResponseEntity<Sunglasses> createSunglasses(@Valid @RequestBody SunglassesDTO sunglassesDTO) {
            // Convert DTO to Entity
            Sunglasses sunglasses = new Sunglasses();
            sunglasses.setBrand(sunglassesDTO.getBrand());
            sunglasses.setName(sunglassesDTO.getName());
            sunglasses.setImage(sunglassesDTO.getImage());
            sunglasses.setPrice(sunglassesDTO.getPrice());
            sunglasses.setFrameColor(sunglassesDTO.getFrameColor());
            sunglasses.setFrameShape(sunglassesDTO.getFrameShape());
            sunglasses.setGlassColor(sunglassesDTO.getGlassColor());
            sunglasses.setWeight(sunglassesDTO.getWeight());

            // Save the Sunglasses entity
            Sunglasses savedSunglasses = sunglassesService.saveSunglasses(sunglasses);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedSunglasses);
        }
    

    @PutMapping("/{id}")
    public ResponseEntity<Sunglasses> updateSunglasses(@PathVariable Long id, @RequestBody Sunglasses sunglassesDetails) {
        Optional<Sunglasses> sunglasses = sunglassesService.getSunglassesById(id);
        if (sunglasses.isPresent()) {
            Sunglasses updatedSunglasses = sunglasses.get();
            updatedSunglasses.setBrand(sunglassesDetails.getBrand());
            updatedSunglasses.setName(sunglassesDetails.getName());
            updatedSunglasses.setImage(sunglassesDetails.getImage());
            updatedSunglasses.setPrice(sunglassesDetails.getPrice());
            updatedSunglasses.setFrameColor(sunglassesDetails.getFrameColor());
            updatedSunglasses.setFrameShape(sunglassesDetails.getFrameShape());
            updatedSunglasses.setGlassColor(sunglassesDetails.getGlassColor());
            updatedSunglasses.setWeight(sunglassesDetails.getWeight());
            return ResponseEntity.ok(sunglassesService.saveSunglasses(updatedSunglasses));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSunglasses(@PathVariable Long id) {
        sunglassesService.deleteSunglasses(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
