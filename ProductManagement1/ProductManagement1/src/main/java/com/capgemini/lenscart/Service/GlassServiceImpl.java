package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenscart.Exception.GlassNotFoundException;
import com.capgemini.lenscart.Repositories.GlassRepo;
import com.capgemini.lenscart.entities.Glass;

@Service
public class GlassServiceImpl implements IGlassService{

    @Autowired
    private GlassRepo glassRepository;

    public List<Glass> getAllGlasses() {
        return glassRepository.findAll();
    }
    @Override
    public Optional<Glass> getGlassById(Long id) {
    	Optional<Glass> glass = glassRepository.findById(id);
        if (glass.isEmpty()) {
            throw new GlassNotFoundException("Glass not found with ID: " + id);
        }
        return glass;
    }
    @Override
    public Glass saveGlass(Glass glass) {
        return glassRepository.save(glass);
    }
    @Override
    public void deleteGlass(Long id) {
    	Optional<Glass> glass = getGlassById(id); // Check if the glass exists
        glassRepository.deleteById(id); // Delete the entity if exists
    } 
    
}
