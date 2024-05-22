package com.dev.backend.service;

import com.dev.backend.dto.CountryDTO;
import com.dev.backend.entity.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    CountryDTO save(Country country);

    Optional<CountryDTO> findById(Integer id);

    List<CountryDTO> findAll();

    void deleteById(Integer id);
}
