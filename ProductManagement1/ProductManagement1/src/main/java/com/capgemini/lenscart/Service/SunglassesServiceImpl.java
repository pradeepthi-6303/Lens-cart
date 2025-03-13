package com.capgemini.lenscart.Service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenscart.Exception.SunglassesNotFoundException;
import com.capgemini.lenscart.Repositories.SunglassesRepo;
import com.capgemini.lenscart.entities.Sunglasses;


@Service
public class SunglassesServiceImpl implements ISunglassesService {

    @Autowired
    private SunglassesRepo sunglassesRepository;

    @Override
    public List<Sunglasses> getAllSunglasses() {
        return sunglassesRepository.findAll();
    }

    @Override
    public Optional<Sunglasses> getSunglassesById(Long id) {
    	Optional<Sunglasses> sunglasses = sunglassesRepository.findById(id);
        if (!sunglasses.isPresent()) {
            throw new SunglassesNotFoundException("Sunglasses not found with ID: " + id);
        }
        return sunglasses;
    }

    @Override
    public Sunglasses saveSunglasses(Sunglasses sunglasses) {
        return sunglassesRepository.save(sunglasses);
    }

    @Override
    
    public Sunglasses deleteSunglasses(Long id) {
    	Optional<Sunglasses> sunglasses = sunglassesRepository.findById(id);
        if (!sunglasses.isPresent()) {
            throw new SunglassesNotFoundException("Sunglasses not found with ID: " + id);
        }

        sunglassesRepository.delete(sunglasses.get());  // Deleting the entity
        return sunglasses.get();  // Returning the deleted entity
    }
}
