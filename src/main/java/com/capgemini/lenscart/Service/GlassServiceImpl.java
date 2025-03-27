package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.lenscart.DTO.GlassDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.entities.Glass;
import com.capgemini.lenscart.entities.Category;
import com.capgemini.lenscart.Repositories.GlassRepo;
import com.capgemini.lenscart.Repositories.CategoryRepo;

@Service
public class GlassServiceImpl implements IGlassService {

    private static final Logger logger = LoggerFactory.getLogger(GlassServiceImpl.class); // Logger initialization

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
        logger.info("Converting GlassDTO to Glass entity for name: {}", glassDTO.getName());
        // Always set categoryId to 2
        Category category = categoryRepository.findById(2L)
                .orElseThrow(() -> {
                    logger.error("Category with ID 2 not found.");
                    return new ResourceNotFoundException("Category not found");
                });
        Glass glass = new Glass();
        glass.setName(glassDTO.getName());
        glass.setImage(glassDTO.getImage());
        glass.setBrand(glassDTO.getBrand());
        glass.setPrice(glassDTO.getPrice());
        glass.setType(glassDTO.getType());
        glass.setPowerRange(glassDTO.getPowerRange());
        glass.setCategory(category); // set the category object here
        logger.info("Successfully converted GlassDTO to Glass entity for name: {}", glassDTO.getName());
        return glass;
    }

    @Override
    public List<GlassDTO> getAllGlasses() {
        logger.info("Fetching all glasses.");
        List<Glass> glasses = glassRepository.findAll();
        logger.info("Successfully fetched {} glasses.", glasses.size());
        return glasses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<GlassDTO> getGlassById(Long id) throws ResourceNotFoundException {
        logger.info("Fetching glass with ID: {}", id);
        Glass glass = glassRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Glass with ID {} not found.", id);
                    return new ResourceNotFoundException("Glass with ID " + id + " not found");
                });
        logger.info("Successfully fetched glass with ID: {}", id);
        return Optional.of(convertToDTO(glass));
    }

    @Override
    public GlassDTO saveGlass(GlassDTO glassDTO) {
        logger.info("Saving new glass with name: {}", glassDTO.getName());
        Glass glass = convertToEntity(glassDTO);
        Glass savedGlass = glassRepository.save(glass);
        logger.info("Successfully saved glass with ID: {}", savedGlass.getId());
        return convertToDTO(savedGlass);
    }

    @Override
    public void deleteGlass(Long id) throws ResourceNotFoundException {
        logger.info("Attempting to delete glass with ID: {}", id);
        Glass glass = glassRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Glass with ID {} not found, cannot delete.", id);
                    return new ResourceNotFoundException("Glass with ID " + id + " not found");
                });
        glassRepository.delete(glass);
        logger.info("Successfully deleted glass with ID: {}", id);
    }
}