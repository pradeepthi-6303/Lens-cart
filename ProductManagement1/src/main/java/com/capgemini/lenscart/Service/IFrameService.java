package com.capgemini.lenscart.Service;

import java.util.List;
import java.util.Optional;

import com.capgemini.lenscart.entities.Frame;

public interface IFrameService {

	Optional<Frame> getFrameById(Long id);

	void deleteFrame(Long id);

	Frame saveFrame(Frame frame);

	List<Frame> getAllFrames();

}
