package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.capgemini.lenscart.Repositories.CategoryRepo;
import com.capgemini.lenscart.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.lenscart.DTO.SunglassesDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.entities.Sunglasses;
import com.capgemini.lenscart.Repositories.SunglassesRepo;

@Service
public class SunglassesServiceImpl implements ISunglassesService {

    private static final Logger logger = LoggerFactory.getLogger(SunglassesServiceImpl.class); // Logger initialization

    @Autowired
    private SunglassesRepo sunglassesRepository;

    @Autowired
    private CategoryRepo categoryRepository;

    // Convert Sunglasses entity to SunglassesDTO
    private SunglassesDTO convertToDTO(Sunglasses sunglasses) {
        return new SunglassesDTO(
                sunglasses.getId(),
                sunglasses.getBrand(),
                sunglasses.getName(),
                sunglasses.getImage(),
                sunglasses.getPrice(),
                sunglasses.getFrameColor(),
                sunglasses.getFrameShape(),
                sunglasses.getGlassColor(),
                sunglasses.getWeight(),
                sunglasses.getCategory().getId()
        );
    }

    // Convert SunglassesDTO to Sunglasses entity
    private Sunglasses convertToEntity(SunglassesDTO sunglassesDTO) {
        // Fetch the Category based on the categoryId in the DTO
        Optional<Category> categoryOptional = categoryRepository.findById(sunglassesDTO.getCategoryId());
        Category category = categoryOptional
                .orElseThrow(() -> {
                    logger.error("Category not found for ID: {}", sunglassesDTO.getCategoryId()); // Logging error
                    return new ResourceNotFoundException("Category not found for ID: " + sunglassesDTO.getCategoryId());
                });

        Sunglasses sunglasses = new Sunglasses();
        sunglasses.setBrand(sunglassesDTO.getBrand());
        sunglasses.setName(sunglassesDTO.getName());
        sunglasses.setImage(sunglassesDTO.getImage());
        sunglasses.setPrice(sunglassesDTO.getPrice());
        sunglasses.setFrameColor(sunglassesDTO.getFrameColor());
        sunglasses.setFrameShape(sunglassesDTO.getFrameShape());
        sunglasses.setGlassColor(sunglassesDTO.getGlassColor());
        sunglasses.setWeight(sunglassesDTO.getWeight());
        sunglasses.setCategory(category); // Set the category here
        return sunglasses;
    }

    @Override
    public List<SunglassesDTO> getAllSunglasses() {
        logger.info("Request received to fetch all sunglasses.");
        List<Sunglasses> sunglasses = sunglassesRepository.findAll();
        if (sunglasses.isEmpty()) {
            logger.warn("No sunglasses found in the database.");
        }
        logger.info("Successfully fetched {} sunglasses.", sunglasses.size());
        return sunglasses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SunglassesDTO> getSunglassesById(Long id) {
        logger.info("Request received to fetch sunglasses with ID: {}", id);
        Optional<Sunglasses> sunglassesOptional = sunglassesRepository.findById(id);
        if (sunglassesOptional.isPresent()) {
            SunglassesDTO sunglassesDTO = convertToDTO(sunglassesOptional.get());
            logger.info("Successfully fetched sunglasses with ID: {}", id);
            return Optional.of(sunglassesDTO);
        } else {
            logger.error("Sunglasses with ID {} not found", id); // Log error if sunglasses not found
            return Optional.empty();
        }
    }

    @Override
    public SunglassesDTO saveSunglasses(SunglassesDTO sunglassesDTO) {
        logger.info("Request received to save a new pair of sunglasses with brand: {}", sunglassesDTO.getBrand());
        Sunglasses sunglasses = convertToEntity(sunglassesDTO);
        Sunglasses savedSunglasses = sunglassesRepository.save(sunglasses);
        logger.info("Successfully saved sunglasses with ID: {}", savedSunglasses.getId());
        return convertToDTO(savedSunglasses);
    }

    @Override
    public void deleteSunglasses(Long id) throws ResourceNotFoundException {
        logger.info("Request received to delete sunglasses with ID: {}", id);
        Sunglasses sunglasses = sunglassesRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Sunglasses with ID {} not found", id); // Log error if sunglasses not found
                    return new ResourceNotFoundException("Sunglasses with ID " + id + " not found");
                });
        sunglassesRepository.delete(sunglasses);
        logger.info("Successfully deleted sunglasses with ID: {}", id); // Log success
    }
}