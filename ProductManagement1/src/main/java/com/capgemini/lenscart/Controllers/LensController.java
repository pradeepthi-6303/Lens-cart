package com.capgemini.lenscart.Controllers;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.lenscart.DTO.LensDTO;
import com.capgemini.lenscart.Service.ILensService;
import com.capgemini.lenscart.entities.Lens;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/lenses")
public class LensController {

    @Autowired
    private ILensService lensService;

    @GetMapping("/all")
    public List<Lens> getAllLenses() {
        return lensService.getAllLenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lens> getLensById(@PathVariable Long id) {
        Optional<Lens> lens = lensService.getLensById(id);
        return lens.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{createLens}")
    public ResponseEntity<Lens> createLens(@Valid @RequestBody LensDTO lensDTO) {
        // Convert DTO to Entity
        Lens lens = new Lens();
        lens.setBrand(lensDTO.getBrand());
        lens.setImage(lensDTO.getImage());
        lens.setShape(lensDTO.getShape());
        lens.setColor(lensDTO.getColor());
        lens.setPrice(lensDTO.getPrice());
        lens.setQuantityInBox(lensDTO.getQuantityInBox());

        // Save the Lens entity
        Lens savedLens = lensService.saveLens(lens);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedLens);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lens> updateLens(@PathVariable Long id, @RequestBody Lens lensDetails) {
        Optional<Lens> lens = lensService.getLensById(id);
        if (lens.isPresent()) {
            Lens updatedLens = lens.get();
            updatedLens.setBrand(lensDetails.getBrand());
            updatedLens.setImage(lensDetails.getImage());
            updatedLens.setShape(lensDetails.getShape());
            updatedLens.setColor(lensDetails.getColor());
            updatedLens.setPrice(lensDetails.getPrice());
            updatedLens.setQuantityInBox(lensDetails.getQuantityInBox());
            return ResponseEntity.ok(lensService.saveLens(updatedLens));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLens(@PathVariable Long id) {
        Optional<Lens> lens = lensService.getLensById(id);
        if (lens.isPresent()) {
            lensService.deleteLens(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
