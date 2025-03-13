package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.lenscart.Exception.FrameNotFoundException;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.Repositories.CategoryRepo;
import com.capgemini.lenscart.Repositories.FrameRepo;
import com.capgemini.lenscart.entities.Category;
import com.capgemini.lenscart.entities.Frame;

@Service
public class FrameServiceImpl implements IFrameService {

    @Autowired
    private FrameRepo frameRepository;
    
    @Autowired
    private CategoryRepo categoryRepository;

    // Create a new Frame
    @Transactional
    public Frame createFrame(Frame frame) {
        // Validate if the category exists and set it to the frame
        Category category = categoryRepository.findById(frame.getCategoryId().getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        frame.setCategoryId(category);
        return frameRepository.save(frame);  // Save and return the created frame
    }

    // Get all frames
    public List<Frame> getAllFrames() {
        return frameRepository.findAll();
    }

    // Get a frame by its ID
    @Override
    public Frame getFrameById(Long id) {
        // Return the frame if found, else throw FrameNotFoundException
        return frameRepository.findById(id)
                .orElseThrow(() -> new FrameNotFoundException("Frame with ID " + id + " not found"));
    }

    // Save a frame (Create or Update)
    @Override
    @Transactional
    public Frame saveFrame(Frame frame) {
        return frameRepository.save(frame);
    }

    // Delete a frame by its ID
    @Override
    @Transactional
    public void deleteFrame(Long id) {
        // Check if the frame exists, else throw exception
        if (!frameRepository.existsById(id)) {
            throw new FrameNotFoundException("Frame with ID " + id + " not found");
        }
        // Delete the frame
        frameRepository.deleteById(id);
    }
}
