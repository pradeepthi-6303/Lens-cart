package com.capgemini.lenscart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.lenscart.entities.Lens;

public interface LensRepo extends JpaRepository <Lens,Long> {

}
