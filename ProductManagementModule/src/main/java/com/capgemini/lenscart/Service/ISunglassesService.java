package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;

import com.capgemini.lenscart.DTO.SunglassesDTO;

public interface ISunglassesService {

    Optional<SunglassesDTO> getSunglassesById(Long id);

    List<SunglassesDTO> getAllSunglasses();

    SunglassesDTO saveSunglasses(SunglassesDTO sunglassesDTO);

    void deleteSunglasses(Long id);

}
