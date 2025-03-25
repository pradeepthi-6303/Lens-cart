package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;

import com.capgemini.lenscart.DTO.GlassDTO;

public interface IGlassService {
    List<GlassDTO> getAllGlasses();
    Optional<GlassDTO> getGlassById(Long id);
    GlassDTO saveGlass(GlassDTO glassDTO);
    void deleteGlass(Long id);
}
