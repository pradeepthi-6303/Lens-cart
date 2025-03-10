package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;

import com.capgemini.lenscart.entities.Glass;

public interface IGlassService {
    List<Glass> getAllGlasses();
    Optional<Glass> getGlassById(Long id);
    Glass saveGlass(Glass glass);
    void deleteGlass(Long id);
}
