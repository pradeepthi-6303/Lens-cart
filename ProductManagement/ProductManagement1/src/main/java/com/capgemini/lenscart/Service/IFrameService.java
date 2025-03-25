package com.capgemini.lenscart.Service;

import java.util.List;

import com.capgemini.lenscart.DTO.FrameDTO;

public interface IFrameService {

    FrameDTO getFrameById(Long id);

    void deleteFrame(Long id);

    FrameDTO saveFrame(FrameDTO frameDTO);

    List<FrameDTO> getAllFrames();

}
