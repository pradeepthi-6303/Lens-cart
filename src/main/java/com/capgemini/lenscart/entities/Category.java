package com.capgemini.lenscart.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Frame> frames;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Glass> glasses;

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Frame> getFrames() {
		return frames;
	}

	public void setFrames(List<Frame> frames) {
		this.frames = frames;
	}

	public List<Glass> getGlasses() {
		return glasses;
	}

	public void setGlasses(List<Glass> glasses) {
		this.glasses = glasses;
	}


}
