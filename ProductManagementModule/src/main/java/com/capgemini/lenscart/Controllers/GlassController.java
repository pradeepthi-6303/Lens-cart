package com.capgemini.lenscart.Controllers;

import com.capgemini.lenscart.DTO.GlassDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.GlassServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/glasses")
public class GlassController {

    @Autowired
    private GlassServiceImpl glassService;

    @GetMapping
    public List<GlassDTO> getAllGlasses() {
        return glassService.getAllGlasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlassDTO> getGlassById(@PathVariable Long id) throws ResourceNotFoundException {
        return glassService.getGlassById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GlassDTO> saveGlass( @RequestBody @Valid  GlassDTO glassDTO) {
        GlassDTO savedGlass = glassService.saveGlass(glassDTO);
        return new ResponseEntity<>(savedGlass, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGlass(@PathVariable Long id) throws ResourceNotFoundException {
        glassService.deleteGlass(id);
        return ResponseEntity.noContent().build();
    }
}
