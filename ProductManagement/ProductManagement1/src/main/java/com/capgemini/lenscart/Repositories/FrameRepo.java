package com.capgemini.lenscart.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.lenscart.entities.Frame;

public interface FrameRepo extends JpaRepository <Frame,Long> {

}
