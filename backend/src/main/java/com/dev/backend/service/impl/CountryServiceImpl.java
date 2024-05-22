package com.dev.backend.service.impl;

import com.dev.backend.dto.CountryDTO;
import com.dev.backend.entity.Country;
import com.dev.backend.exception.ResourceNotFoundException;
import com.dev.backend.repository.CountryRepository;
import com.dev.backend.service.CountryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CountryDTO save(Country country) {
        Country dbCountry = countryRepository.save(country);
        return modelMapper.map(dbCountry, CountryDTO.class);
    }

    @Override
    public Optional<CountryDTO> findById(Integer id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent()) {
            return Optional.ofNullable(modelMapper.map(optionalCountry.get(), CountryDTO.class));
        } else {
            throw new ResourceNotFoundException("Cannot find country with id: " + id);
        }
    }

    @Override
    public List<CountryDTO> findAll() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream()
                .map(country -> modelMapper.map(country, CountryDTO.class))
                .toList();
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent()) {
            countryRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Cannot find country with id: " + id);
        }
    }
}
