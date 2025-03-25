package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;

import com.capgemini.lenscart.DTO.LensDTO;

public interface ILensService {
    List<LensDTO> getAllLenses();
    Optional<LensDTO> getLensById(Long id);
    LensDTO saveLens(LensDTO lensDTO);
    void deleteLens(Long id);
}
