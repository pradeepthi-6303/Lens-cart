package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.lenscart.DTO.FrameDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.entities.Frame;
import com.capgemini.lenscart.entities.Category;
import com.capgemini.lenscart.Repositories.FrameRepo;
import com.capgemini.lenscart.Repositories.CategoryRepo;

@Service
public class FrameServiceImpl implements IFrameService {

    private static final Logger logger = LoggerFactory.getLogger(FrameServiceImpl.class); // Logger initialization

    @Autowired
    private FrameRepo frameRepository;

    @Autowired
    private CategoryRepo categoryRepository;

    // Convert Frame entity to FrameDTO
    private FrameDTO convertToDTO(Frame frame) {
        return new FrameDTO(frame.getId(), frame.getName(), frame.getColor(), frame.getPrice(),
                frame.getDescription(), frame.getShape(), frame.getSize(), frame.getCategory().getId());
    }

    // Convert FrameDTO to Frame entity
    private Frame convertToEntity(FrameDTO frameDTO) {
        // Default category ID (could be dynamic based on your needs)
        Category category = categoryRepository.findById(4L)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Frame frame = new Frame();
        frame.setName(frameDTO.getName());
        frame.setColor(frameDTO.getColor());
        frame.setPrice(frameDTO.getPrice());
        frame.setDescription(frameDTO.getDescription());
        frame.setShape(frameDTO.getShape());
        frame.setSize(frameDTO.getSize());

        frame.setCategory(category); // Set Category entity in Frame

        return frame;
    }

    @Override
    public List<FrameDTO> getAllFrames() {
        logger.info("Fetching all frames");
        List<Frame> frames = frameRepository.findAll();
        logger.info("Successfully fetched {} frames", frames.size());
        return frames.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FrameDTO getFrameById(Long id) throws ResourceNotFoundException {
        logger.info("Fetching frame with ID: {}", id);
        Optional<Frame> frameOptional = frameRepository.findById(id);
        if (!frameOptional.isPresent()) {
            logger.error("Frame with ID {} not found", id); // Log the error if frame is not found
            throw new ResourceNotFoundException("Frame with ID " + id + " not found"); // Throw exception
        }
        Frame frame = frameOptional.get();
        logger.info("Successfully fetched frame with ID: {}", id); // Log successful fetch
        return convertToDTO(frame);
    }

    @Override
    public FrameDTO saveFrame(FrameDTO frameDTO) {
        logger.info("Saving a new frame with name: {}", frameDTO.getName());
        Frame frame = convertToEntity(frameDTO);
        Frame savedFrame = frameRepository.save(frame);
        logger.info("Successfully saved frame with ID: {}", savedFrame.getId());
        return convertToDTO(savedFrame);
    }

    @Override
    public void deleteFrame(Long id) throws ResourceNotFoundException {
        logger.info("Attempting to delete frame with ID: {}", id);
        Optional<Frame> frameOptional = frameRepository.findById(id);
        if (!frameOptional.isPresent()) {
            logger.error("Frame with ID {} not found", id); // Log the error if frame is not found
            throw new ResourceNotFoundException("Frame with ID " + id + " not found"); // Throw exception
        }
        Frame frame = frameOptional.get();
        frameRepository.delete(frame);
        logger.info("Successfully deleted frame with ID: {}", id); // Log successful deletion
    }
}