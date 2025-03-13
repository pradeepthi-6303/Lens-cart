package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.lenscart.Exception.LensNotFoundException;
import com.capgemini.lenscart.Repositories.LensRepo;
import com.capgemini.lenscart.entities.Lens;


@Service
public class LensServiceImpl implements ILensService {

    @Autowired
    private LensRepo lensRepository;

    @Override
    public List<Lens> getAllLenses() {
        return lensRepository.findAll();
    }

    @Override
    public Optional<Lens> getLensById(Long id) {
    	Optional<Lens> lens = lensRepository.findById(id);
        if (!lens.isPresent()) {
            throw new LensNotFoundException("Lens not found with ID: " + id);
        }
        return lens; 
    }

    @Override
    public Lens saveLens(Lens lens) {
        return lensRepository.save(lens);
    }

    @Override
    public void deleteLens(Long id) {
    	Optional<Lens> lens = getLensById(id); // Check if the lens exists
        if (!lens.isPresent()) {
            throw new LensNotFoundException("Lens not found with ID: " + id);
        }
        lensRepository.deleteById(id); // Delete the lens if found
    } 
}
