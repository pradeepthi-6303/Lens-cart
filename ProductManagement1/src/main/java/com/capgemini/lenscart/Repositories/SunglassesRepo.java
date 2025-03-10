package com.capgemini.lenscart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.lenscart.entities.Sunglasses;

public interface SunglassesRepo extends JpaRepository<Sunglasses,Long>{
	//void deleteById();
}
