package com.dev.backend.rest;

import com.dev.backend.dto.StateDTO;
import com.dev.backend.entity.State;
import com.dev.backend.service.StateService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class StateRestController {

    private final StateService stateService;

    @PostMapping("/states")
    @Transactional
    public StateDTO addState(@RequestBody State state) {
        return stateService.save(state);
    }

    @GetMapping("/states/{id}")
    public Optional<StateDTO> getState(@PathVariable Integer id) {
        return stateService.findById(id);
    }

    @GetMapping("/states")
    public List<StateDTO> getStates() {
        return stateService.findAll();
    }

    @RequestMapping(value = "/states/search",
                    method = RequestMethod.GET,
                    params = "code")
    public List<StateDTO> getStatesByCode(@RequestParam String code) {
        return stateService.findByCode(code);
    }

    @DeleteMapping("/states/{id}")
    @Transactional
    public void deleteState(@PathVariable Integer id) {
        stateService.deleteById(id);
    }
}
