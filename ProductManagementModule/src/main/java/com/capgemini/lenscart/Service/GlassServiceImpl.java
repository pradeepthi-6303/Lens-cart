package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenscart.DTO.GlassDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.entities.Glass;
import com.capgemini.lenscart.entities.Category;
import com.capgemini.lenscart.Repositories.GlassRepo;
import com.capgemini.lenscart.Repositories.CategoryRepo;

@Service
public class GlassServiceImpl implements IGlassService {

    @Autowired
    private GlassRepo glassRepository;

    @Autowired
    private CategoryRepo categoryRepository;

    // Convert Glass entity to GlassDTO
    private GlassDTO convertToDTO(Glass glass) {
        return new GlassDTO(glass.getId(), glass.getName(), glass.getImage(), glass.getBrand(),
                glass.getPrice(), glass.getType(), glass.getPowerRange(), glass.getCategory().getId());
    }

    // Convert GlassDTO to Glass entity
    private Glass convertToEntity(GlassDTO glassDTO) {
        // Always set categoryId to 2
        Category category = categoryRepository.findById(2L)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Glass glass = new Glass();
        glass.setName(glassDTO.getName());
        glass.setImage(glassDTO.getImage());
        glass.setBrand(glassDTO.getBrand());
        glass.setPrice(glassDTO.getPrice());
        glass.setType(glassDTO.getType());
        glass.setPowerRange(glassDTO.getPowerRange());
        glass.setCategory(category); // set the category object here
        return glass;
    }

    @Override
    public List<GlassDTO> getAllGlasses() {
        List<Glass> glasses = glassRepository.findAll();
        return glasses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GlassDTO> getGlassById(Long id) throws ResourceNotFoundException {
        Glass glass = glassRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Glass with ID " + id + " not found"));
        return Optional.of(convertToDTO(glass));
    }

    @Override
    public GlassDTO saveGlass(GlassDTO glassDTO) {
        Glass glass = convertToEntity(glassDTO);

        Glass savedGlass = glassRepository.save(glass);
        return convertToDTO(savedGlass);
    }

    @Override
    public void deleteGlass(Long id) throws ResourceNotFoundException {
        Glass glass = glassRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Glass with ID " + id + " not found"));
        glassRepository.delete(glass);
    }
}
