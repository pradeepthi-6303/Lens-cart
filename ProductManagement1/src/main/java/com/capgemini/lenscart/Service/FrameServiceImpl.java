package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenscart.Exception.FrameNotFoundException;
import com.capgemini.lenscart.Repositories.FrameRepo;
import com.capgemini.lenscart.entities.Frame;

@Service
public class FrameServiceImpl implements IFrameService  {

    @Autowired
    private FrameRepo frameRepository;

    public List<Frame> getAllFrames() {
        return frameRepository.findAll();
    }
    @Override
    public Optional<Frame> getFrameById(Long id) {
    	Optional<Frame> frame = frameRepository.findById(id);
        if (frame.isEmpty()) {
            throw new FrameNotFoundException("Frame with ID " + id + " not found");
        }
        return frame;
        
    }
    @Override
    public Frame saveFrame(Frame frame) {
        return frameRepository.save(frame);
    }
    @Override
    public void deleteFrame(Long id) {
    	Optional<Frame> frame = frameRepository.findById(id);
        if (frame.isEmpty()) {
            throw new FrameNotFoundException("Frame with ID " + id + " not found");
        }
        frameRepository.deleteById(id);
    }
}
