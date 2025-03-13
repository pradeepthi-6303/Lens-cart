package com.capgemini.lenscart.Service;


import java.util.List;
import java.util.Optional;

import com.capgemini.lenscart.entities.Lens;

public interface ILensService {
    List<Lens> getAllLenses();
    Optional<Lens> getLensById(Long id);
    Lens saveLens(Lens lens);
	void deleteLens(Long id);
    
}
