package com.dev.backend.service;

import com.dev.backend.dto.StateDTO;
import com.dev.backend.entity.State;

import java.util.List;
import java.util.Optional;

public interface StateService {

    StateDTO save(State state);

    Optional<StateDTO> findById(Integer id);

    List<StateDTO> findAll();

    List<StateDTO> findByCode(String code);

    void deleteById(Integer id);
}
