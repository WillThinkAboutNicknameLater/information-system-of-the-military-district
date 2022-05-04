package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.DislocationDto;
import ru.nsu.fit.militarysystem.service.DislocationService;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.DislocationSearchFilter;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class DislocationController {
    private final DislocationService dislocationService;

    private static final String GET_DISLOCATIONS = "/dislocations";

    private static final String GET_DISLOCATIONS_BY_SEARCH_FILTER = "/dislocations/search";

    private static final String GET_DISLOCATION = "/dislocations/{id}";

    private static final String POST_DISLOCATION = "/dislocations";

    private static final String PUT_DISLOCATION = "/dislocations/{id}";

    private static final String DELETE_DISLOCATION = "/dislocations/{id}";

    public DislocationController(DislocationService dislocationService) {
        this.dislocationService = dislocationService;
    }

    @GetMapping(GET_DISLOCATIONS)
    public ResponseEntity<List<DislocationDto>> getAllDislocations() {
        List<DislocationDto> dislocationDtos = dislocationService.getAllDislocationsAsDtos();
        return new ResponseEntity<>(dislocationDtos, HttpStatus.OK);
    }

    @GetMapping(GET_DISLOCATIONS_BY_SEARCH_FILTER)
    public ResponseEntity<Page<DislocationDto>> getAllDislocationsByFilter(@RequestBody(required = false) DislocationSearchFilter dislocationSearchFilter) throws EntityNotFoundException {
        Page<DislocationDto> dislocationDtos = dislocationService.getAllDislocationsByFilterAsDtos(dislocationSearchFilter);
        return new ResponseEntity<>(dislocationDtos, HttpStatus.OK);
    }

    @GetMapping(GET_DISLOCATION)
    public ResponseEntity<DislocationDto> getDislocationById(@PathVariable("id") short id) throws EntityNotFoundException {
        DislocationDto dislocationDto = dislocationService.getDislocationByIdAsDto(id);
        return new ResponseEntity<>(dislocationDto, HttpStatus.OK);
    }

    @PostMapping(POST_DISLOCATION)
    public ResponseEntity<DislocationDto> createDislocation(@RequestBody DislocationDto dislocationDto) {
        return new ResponseEntity<>(dislocationService.createDislocation(dislocationDto), HttpStatus.OK);
    }

    @PutMapping(PUT_DISLOCATION)
    public ResponseEntity<DislocationDto> updateDislocationById(@PathVariable("id") short id, @RequestBody DislocationDto dislocationDto) throws EntityNotFoundException {
        return new ResponseEntity<>(dislocationService.updateDislocationById(id, dislocationDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_DISLOCATION)
    public ResponseEntity<DislocationDto> deleteDislocationById(@PathVariable("id") short id) throws EntityNotFoundException {
        DislocationDto dislocationDto = dislocationService.deleteDislocationById(id);
        return new ResponseEntity<>(dislocationDto, HttpStatus.OK);
    }

}
