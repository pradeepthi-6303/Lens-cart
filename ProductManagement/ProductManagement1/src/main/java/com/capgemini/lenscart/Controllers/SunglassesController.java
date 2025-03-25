package com.capgemini.lenscart.Controllers;

import com.capgemini.lenscart.DTO.SunglassesDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.SunglassesServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sunglasses")
public class SunglassesController {

    @Autowired
    private SunglassesServiceImpl sunglassesService;

    @GetMapping("/all")
    public List<SunglassesDTO> getAllSunglasses() {
        return sunglassesService.getAllSunglasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SunglassesDTO> getSunglassesById(@PathVariable Long id) throws ResourceNotFoundException {
        return sunglassesService.getSunglassesById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<SunglassesDTO> saveSunglasses(@Valid @RequestBody SunglassesDTO sunglassesDTO) {
        SunglassesDTO savedSunglasses = sunglassesService.saveSunglasses(sunglassesDTO);
        return new ResponseEntity<>(savedSunglasses, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSunglasses(@PathVariable Long id) throws ResourceNotFoundException {
        sunglassesService.deleteSunglasses(id);
        return ResponseEntity.noContent().build();
    }
}
