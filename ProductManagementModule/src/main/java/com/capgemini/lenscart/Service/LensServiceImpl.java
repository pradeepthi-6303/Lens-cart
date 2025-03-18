package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenscart.DTO.LensDTO;
import com.capgemini.lenscart.Exception.ResourceNotFoundException;
import com.capgemini.lenscart.entities.Lens;
import com.capgemini.lenscart.Repositories.LensRepo;

@Service
public class LensServiceImpl implements ILensService {

    @Autowired
    private LensRepo lensRepository;

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
                lens.getId()  // Ensure we are passing categoryId as is from the entity
        );
    }

    // Convert LensDTO to Lens entity
    private Lens convertToEntity(LensDTO lensDTO) {
        Lens lens = new Lens();
        lens.setBrand(lensDTO.getBrand());
        lens.setImage(lensDTO.getImage());
        lens.setShape(lensDTO.getShape());
        lens.setColor(lensDTO.getColor());
        lens.setPrice(lensDTO.getPrice());
        lens.setQuantityInBox(lensDTO.getQuantityInBox());
        lens.setId(lensDTO.getCategoryId()); // Setting categoryId as is
        return lens;
    }

    @Override
    public List<LensDTO> getAllLenses() {
        List<Lens> lenses = lensRepository.findAll();
        return lenses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<LensDTO> getLensById(Long id) throws ResourceNotFoundException {
        Lens lens = lensRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lens with ID " + id + " not found"));
        return Optional.of(convertToDTO(lens));
    }

    @Override
    public LensDTO saveLens(LensDTO lensDTO) {
        Lens lens = convertToEntity(lensDTO);
        Lens savedLens = lensRepository.save(lens);
        return convertToDTO(savedLens);
    }

    @Override
    public void deleteLens(Long id) throws ResourceNotFoundException {
        Lens lens = lensRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lens with ID " + id + " not found"));
        lensRepository.delete(lens);
    }
}