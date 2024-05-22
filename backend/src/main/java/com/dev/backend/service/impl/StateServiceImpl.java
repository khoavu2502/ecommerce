package com.dev.backend.service.impl;

import com.dev.backend.dto.StateDTO;
import com.dev.backend.entity.State;
import com.dev.backend.exception.ResourceNotFoundException;
import com.dev.backend.repository.StateRepository;
import com.dev.backend.service.StateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public StateDTO save(State state) {
        return modelMapper.map(stateRepository.save(state), StateDTO.class);
    }

    @Override
    public Optional<StateDTO> findById(Integer id) {
        Optional<State> optionalState = stateRepository.findById(id);
        if (optionalState.isPresent()) {
            return Optional.ofNullable(modelMapper.map(optionalState.get(), StateDTO.class));
        } else {
            throw new ResourceNotFoundException("Cannot find state with id: " + id);
        }
    }

    @Override
    public List<StateDTO> findAll() {
        List<State> states = stateRepository.findAll();
        return states.stream()
                .map(state -> modelMapper.map(state, StateDTO.class))
                .toList();
    }

    @Override
    public List<StateDTO> findByCode(String code) {
        List<State> states = stateRepository.findByCountryCode(code);
        return states.stream()
                .map(state -> modelMapper.map(state, StateDTO.class))
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<State> optionalState = stateRepository.findById(id);
        if (optionalState.isPresent()) {
            stateRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Cannot find state with id: " + id);
        }
    }
}
