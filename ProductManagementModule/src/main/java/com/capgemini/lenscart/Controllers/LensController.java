package com.capgemini.lenscart.Controllers;

import com.capgemini.lenscart.DTO.LensDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.LensServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/lenses")
public class LensController {

    @Autowired
    private LensServiceImpl lensService;

    @GetMapping("/all")
    public List<LensDTO> getAllLenses() {
        return lensService.getAllLenses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LensDTO> getLensById(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<LensDTO> lensDTO = lensService.getLensById(id);
        return lensDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<LensDTO> saveLens( @Valid @RequestBody LensDTO lensDTO) {
        LensDTO savedLens = lensService.saveLens(lensDTO);
        return new ResponseEntity<>(savedLens, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLens(@PathVariable Long id) throws ResourceNotFoundException {
        lensService.deleteLens(id);
        return ResponseEntity.noContent().build();
    }
}
