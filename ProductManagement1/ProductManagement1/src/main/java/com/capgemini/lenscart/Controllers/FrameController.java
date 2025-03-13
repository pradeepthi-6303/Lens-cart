package com.capgemini.lenscart.Controllers;

import java.util.List;

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

    // Get all frames
    @GetMapping("/all")
    public ResponseEntity<List<Frame>> getAllFrames() {
        List<Frame> frames = frameService.getAllFrames();
        return ResponseEntity.ok(frames); // Return all frames
    }

    // Get a frame by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Frame> getFrameById(@PathVariable Long id) {
        try {
            Frame frame = frameService.getFrameById(id); // Fetch the frame by ID
            return ResponseEntity.ok(frame); // Return the frame if found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return not found if exception occurs
        }
    }

    // Create a new frame
    @PostMapping("/create")
    public ResponseEntity<Frame> createFrame(@Valid @RequestBody FrameDTO frameDTO) {
        // Convert DTO to entity and handle the creation of the frame
        Frame frame = new Frame();
        frame.setName(frameDTO.getName());
        frame.setColor(frameDTO.getColor());
        frame.setPrice(frameDTO.getPrice());
        frame.setDescription(frameDTO.getDescription());
        frame.setShape(frameDTO.getShape());
        frame.setSize(frameDTO.getSize());

        // Create the frame using service
        try {
            Frame savedFrame = frameService.createFrame(frame);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFrame); // Return the created frame
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Return bad request if there's an issue
        }
    }

    // Update an existing frame
    @PutMapping("/{id}")
    public ResponseEntity<Frame> updateFrame(@PathVariable Long id, @RequestBody Frame frameDetails) {
        try {
            // Fetch the frame by ID and update the details
            Frame updatedFrame = frameService.getFrameById(id); // Get existing frame
            updatedFrame.setName(frameDetails.getName());
            updatedFrame.setColor(frameDetails.getColor());
            updatedFrame.setPrice(frameDetails.getPrice());
            updatedFrame.setDescription(frameDetails.getDescription());
            updatedFrame.setShape(frameDetails.getShape());
            updatedFrame.setSize(frameDetails.getSize());

            // Save the updated frame
            Frame savedFrame = frameService.saveFrame(updatedFrame);
            return ResponseEntity.ok(savedFrame); // Return updated frame
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return not found if the frame is not found
        }
    }

    // Delete a frame by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFrame(@PathVariable Long id) {
        try {
            frameService.deleteFrame(id); // Call delete service method
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Return success response with no content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return not found if the frame doesn't exist
        }
    }
}
