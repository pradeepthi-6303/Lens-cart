package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenscart.DTO.FrameDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.entities.Frame;
import com.capgemini.lenscart.entities.Category; // Import the Category entity
import com.capgemini.lenscart.Repositories.FrameRepo;
import com.capgemini.lenscart.Repositories.CategoryRepo; // Make sure to import the Category repository

@Service
public class FrameServiceImpl implements IFrameService {

    @Autowired
    private FrameRepo frameRepository;

    @Autowired
    private CategoryRepo categoryRepository; // Inject the Category repository

    // Convert Frame entity to FrameDTO
    private FrameDTO convertToDTO(Frame frame) {
        return new FrameDTO(frame.getId(), frame.getName(), frame.getColor(), frame.getPrice(),
                frame.getDescription(), frame.getShape(), frame.getSize(), frame.getCategory().getId());
    }

    // Convert FrameDTO to Frame entity
    private Frame convertToEntity(FrameDTO frameDTO) {
        Category category = categoryRepository.findById(4L)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Frame frame = new Frame();
        frame.setName(frameDTO.getName());
        frame.setColor(frameDTO.getColor());
        frame.setPrice(frameDTO.getPrice());
        frame.setDescription(frameDTO.getDescription());
        frame.setShape(frameDTO.getShape());
        frame.setSize(frameDTO.getSize());

        // Fetch Category entity from categoryId
        frame.setCategory(category); // Set Category entity in Frame

        return frame;
    }

    @Override
    public List<FrameDTO> getAllFrames() {
        List<Frame> frames = frameRepository.findAll();
        return frames.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FrameDTO getFrameById(Long id) throws ResourceNotFoundException {
        Frame frame = frameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Frame with ID " + id + " not found"));
        return convertToDTO(frame);
    }

    @Override
    public FrameDTO saveFrame(FrameDTO frameDTO) {
        Frame frame = convertToEntity(frameDTO);
        Frame savedFrame = frameRepository.save(frame);
        return convertToDTO(savedFrame);
    }

    @Override
    public void deleteFrame(Long id) throws ResourceNotFoundException {
        Frame frame = frameRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Frame with ID " + id + " not found"));
        frameRepository.delete(frame);
    }
}
