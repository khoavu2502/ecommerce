package com.dev.backend.rest;

import com.dev.backend.dto.CountryDTO;
import com.dev.backend.entity.Country;
import com.dev.backend.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CountryRestController {

    private final CountryService countryService;

    @PostMapping("/countries")
    public CountryDTO addCountry(@RequestBody Country country) {
        return countryService.save(country);
    }

    @GetMapping("/countries/{id}")
    public Optional<CountryDTO> getCountry(@PathVariable Integer id) {
        return countryService.findById(id);
    }

    @GetMapping("/countries")
    public List<CountryDTO> getCountries() {
        return countryService.findAll();
    }

    @DeleteMapping("/countries/{id}")
    public void deleteCountry(@PathVariable Integer id) {
        countryService.deleteById(id);
    }
}
