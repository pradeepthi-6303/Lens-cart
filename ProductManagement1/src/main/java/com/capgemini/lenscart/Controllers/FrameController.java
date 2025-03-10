package com.capgemini.lenscart.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.lenscart.DTO.FrameDTO;
import com.capgemini.lenscart.Service.FrameServiceImpl;
import com.capgemini.lenscart.entities.Frame;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/frames")
public class FrameController {

    @Autowired
    private FrameServiceImpl frameService;

    @GetMapping("/all")
    public List<Frame> getAllFrames() {
        return frameService.getAllFrames();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Frame> getFrameById(@PathVariable Long id) {
        Optional<Frame> frame = frameService.getFrameById(id);
        return frame.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{createFrame}")
    public ResponseEntity<Frame> createFrame(@Valid @RequestBody FrameDTO frameDTO) {
        // Convert DTO to Entity
        Frame frame = new Frame();
        frame.setName(frameDTO.getName());
        frame.setColor(frameDTO.getColor());
        frame.setPrice(frameDTO.getPrice());
        frame.setDescription(frameDTO.getDescription());
        frame.setShape(frameDTO.getShape());
        frame.setSize(frameDTO.getSize());

        Frame savedFrame = frameService.saveFrame(frame);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFrame);
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<Frame> updateFrame(@PathVariable Long id, @RequestBody Frame frameDetails) {
        Optional<Frame> frame = frameService.getFrameById(id);
        if (frame.isPresent()) {
            Frame updatedFrame = frame.get();
            updatedFrame.setName(frameDetails.getName());
            updatedFrame.setColor(frameDetails.getColor());
            updatedFrame.setPrice(frameDetails.getPrice());
            updatedFrame.setDescription(frameDetails.getDescription());
            updatedFrame.setShape(frameDetails.getShape());
            updatedFrame.setSize(frameDetails.getSize());
            return ResponseEntity.ok(frameService.saveFrame(updatedFrame));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFrame(@PathVariable Long id) {
        frameService.deleteFrame(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
