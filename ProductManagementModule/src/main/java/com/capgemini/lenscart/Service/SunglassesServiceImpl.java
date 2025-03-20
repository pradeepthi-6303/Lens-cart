package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.capgemini.lenscart.Repositories.CategoryRepo;
import com.capgemini.lenscart.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenscart.DTO.SunglassesDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.entities.Sunglasses;
import com.capgemini.lenscart.Repositories.SunglassesRepo;

@Service
public class SunglassesServiceImpl implements ISunglassesService {

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
        Category category = categoryRepository.findById(3L)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        Sunglasses sunglasses = new Sunglasses();
        sunglasses.setBrand(sunglassesDTO.getBrand());
        sunglasses.setName(sunglassesDTO.getName());
        sunglasses.setImage(sunglassesDTO.getImage());
        sunglasses.setPrice(sunglassesDTO.getPrice());
        sunglasses.setFrameColor(sunglassesDTO.getFrameColor());
        sunglasses.setFrameShape(sunglassesDTO.getFrameShape());
        sunglasses.setGlassColor(sunglassesDTO.getGlassColor());
        sunglasses.setWeight(sunglassesDTO.getWeight());
//        sunglasses.setId(sunglassesDTO.getCategoryId()); // Setting categoryId as is
        sunglasses.setCategory(category);
        return sunglasses;
    }

    @Override
    public List<SunglassesDTO> getAllSunglasses() {
        List<Sunglasses> sunglasses = sunglassesRepository.findAll();
        return sunglasses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SunglassesDTO> getSunglassesById(Long id) throws ResourceNotFoundException {
        Sunglasses sunglasses = sunglassesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sunglasses with ID " + id + " not found"));
        return Optional.of(convertToDTO(sunglasses));
    }

    @Override
    public SunglassesDTO saveSunglasses(SunglassesDTO sunglassesDTO) {
        Sunglasses sunglasses = convertToEntity(sunglassesDTO);
        Sunglasses savedSunglasses = sunglassesRepository.save(sunglasses);
        return convertToDTO(savedSunglasses);
    }

    @Override
    public void deleteSunglasses(Long id) throws ResourceNotFoundException {
        Sunglasses sunglasses = sunglassesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sunglasses with ID " + id + " not found"));
        sunglassesRepository.delete(sunglasses);
    }
}