package com.capgemini.lenscart.Controllers;

import com.capgemini.lenscart.DTO.FrameDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.FrameServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frames")
public class FrameController {

    @Autowired
    private FrameServiceImpl frameService;

    @GetMapping
    public List<FrameDTO> getAllFrames() {
        return frameService.getAllFrames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FrameDTO> getFrameById(@PathVariable Long id) throws ResourceNotFoundException {
        FrameDTO frameDTO = frameService.getFrameById(id);
        return ResponseEntity.ok(frameDTO);  // Return FrameDTO directly with a 200 OK status
    }

    @PostMapping
    public ResponseEntity<FrameDTO> saveFrame(@RequestBody @Valid FrameDTO frameDTO) {
        FrameDTO savedFrame = frameService.saveFrame(frameDTO);
        return new ResponseEntity<>(savedFrame, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFrame(@PathVariable Long id) throws ResourceNotFoundException {
        frameService.deleteFrame(id);
        return ResponseEntity.noContent().build();
    }
}
