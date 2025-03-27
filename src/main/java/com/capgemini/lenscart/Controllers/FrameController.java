package com.capgemini.lenscart.Controllers;

import com.capgemini.lenscart.DTO.FrameDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Service.FrameServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/frames")
public class FrameController {

    private static final Logger logger = LoggerFactory.getLogger(FrameController.class); // Logger initialization

    @Autowired
    private FrameServiceImpl frameService;

    @GetMapping
    public List<FrameDTO> getAllFrames() {
        logger.info("Request received to fetch all frames.");
        List<FrameDTO> frames = frameService.getAllFrames();
        logger.info("Successfully fetched {} frames.", frames.size());
        return frames;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FrameDTO> getFrameById(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Request received to fetch frame with ID: {}", id);
        try {
            FrameDTO frameDTO = frameService.getFrameById(id);
            logger.info("Successfully fetched frame with ID: {}", id);
            return ResponseEntity.ok(frameDTO);  // Return FrameDTO directly with a 200 OK status
        } catch (ResourceNotFoundException e) {
            logger.error("Frame with ID {} not found", id, e); // Log error with exception
            throw e;  // Re-throw the exception to be handled by the global exception handler
        }
    }

    @PostMapping
    public ResponseEntity<FrameDTO> saveFrame(@RequestBody @Valid FrameDTO frameDTO) {
        logger.info("Request received to save a new frame with name: {}", frameDTO.getName());
        FrameDTO savedFrame = frameService.saveFrame(frameDTO);
        logger.info("Successfully saved frame with ID: {}", savedFrame.getId());
        return new ResponseEntity<>(savedFrame, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFrame(@PathVariable Long id) throws ResourceNotFoundException {
        logger.info("Request received to delete frame with ID: {}", id);
        try {
            frameService.deleteFrame(id);
            logger.info("Successfully deleted frame with ID: {}", id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            logger.error("Frame with ID {} not found, cannot delete.", id, e);
            throw e;  // Re-throw the exception to be handled by the global exception handler
        }
    }
}