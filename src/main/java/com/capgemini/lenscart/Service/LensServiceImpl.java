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

import com.capgemini.lenscart.DTO.LensDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.entities.Lens;
import com.capgemini.lenscart.Repositories.LensRepo;

@Service
public class LensServiceImpl implements ILensService {

    private static final Logger logger = LoggerFactory.getLogger(LensServiceImpl.class); // Logger initialization

    @Autowired
    private LensRepo lensRepository;

    @Autowired
    private CategoryRepo categoryRepository;

    // Convert Lens entity to LensDTO
    private LensDTO convertToDTO(Lens lens) {
        return new LensDTO(
                lens.getId(),
                lens.getBrand(),
                lens.getImage(),
                lens.getShape(),
                lens.getColor(),
                lens.getPrice(),
                lens.getQuantityInBox(),
                lens.getCategory().getId()
        );
    }

    // Convert LensDTO to Lens entity
    private Lens convertToEntity(LensDTO lensDTO) {
        Category category = categoryRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Lens lens = new Lens();
        lens.setBrand(lensDTO.getBrand());
        lens.setImage(lensDTO.getImage());
        lens.setShape(lensDTO.getShape());
        lens.setColor(lensDTO.getColor());
        lens.setPrice(lensDTO.getPrice());
        lens.setQuantityInBox(lensDTO.getQuantityInBox());
        lens.setCategory(category); // Setting categoryId as is
        return lens;
    }

    @Override
    public List<LensDTO> getAllLenses() {
        logger.info("Fetching all lenses.");
        List<Lens> lenses = lensRepository.findAll();
        logger.info("Successfully fetched {} lenses.", lenses.size());
        return lenses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LensDTO> getLensById(Long id) throws ResourceNotFoundException {
        logger.info("Fetching lens with ID: {}", id);
        Lens lens = lensRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Lens with ID {} not found", id); // Log error if lens not found
                    return new ResourceNotFoundException("Lens with ID " + id + " not found");
                });
        logger.info("Successfully fetched lens with ID: {}", id); // Log success
        return Optional.of(convertToDTO(lens));
    }

    @Override
    public LensDTO saveLens(LensDTO lensDTO) {
        logger.info("Saving a new lens with brand: {}", lensDTO.getBrand());
        Lens lens = convertToEntity(lensDTO);
        Lens savedLens = lensRepository.save(lens);
        logger.info("Successfully saved lens with ID: {}", savedLens.getId());
        return convertToDTO(savedLens);
    }

    @Override
    public void deleteLens(Long id) throws ResourceNotFoundException {
        logger.info("Attempting to delete lens with ID: {}", id);
        Lens lens = lensRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Lens with ID {} not found", id); // Log error if lens not found
                    return new ResourceNotFoundException("Lens with ID " + id + " not found");
                });
        lensRepository.delete(lens);
        logger.info("Successfully deleted lens with ID: {}", id); // Log success
    }
}