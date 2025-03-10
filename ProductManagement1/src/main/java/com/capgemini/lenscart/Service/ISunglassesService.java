package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;

import com.capgemini.lenscart.entities.Sunglasses;

public interface ISunglassesService {

	Optional<Sunglasses> getSunglassesById(Long id);

	List<Sunglasses> getAllSunglasses();

	Sunglasses saveSunglasses(Sunglasses sunglasses);

	Sunglasses deleteSunglasses(Long id);

}
