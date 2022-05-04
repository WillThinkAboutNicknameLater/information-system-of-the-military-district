package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.DislocationTypeDto;
import ru.nsu.fit.militarysystem.service.DislocationTypeService;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.DislocationTypeSearchFilter;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class DislocationTypeController {
    private final DislocationTypeService dislocationTypeService;

    private static final String GET_DISLOCATION_TYPES= "/dislocation-types";

    private static final String GET_DISLOCATION_TYPES_BY_SEARCH_FILTER = "/dislocation-types/search";

    private static final String GET_DISLOCATION_TYPE = "/dislocation-types/{id}";

    private static final String POST_DISLOCATION_TYPE = "/dislocation-types";

    private static final String PUT_DISLOCATION_TYPE = "/dislocation-types/{id}";

    private static final String DELETE_DISLOCATION_TYPE = "/dislocation-types/{id}";

    public DislocationTypeController(DislocationTypeService dislocationTypeService) {
        this.dislocationTypeService = dislocationTypeService;
    }

    @GetMapping(GET_DISLOCATION_TYPES)
    public ResponseEntity<List<DislocationTypeDto>> getAllDislocationTypes() {
        List<DislocationTypeDto> dislocationTypeDtos = dislocationTypeService.getAllDislocationTypesAsDtos();
        return new ResponseEntity<>(dislocationTypeDtos, HttpStatus.OK);
    }

    @GetMapping(GET_DISLOCATION_TYPES_BY_SEARCH_FILTER)
    public ResponseEntity<Page<DislocationTypeDto>> getAllDislocationTypesByFilter(@RequestBody(required = false) DislocationTypeSearchFilter dislocationTypeSearchFilter) throws EntityNotFoundException {
        Page<DislocationTypeDto> dislocationTypeDtos = dislocationTypeService.getAllDislocationTypesByFilterAsDtos(dislocationTypeSearchFilter);
        return new ResponseEntity<>(dislocationTypeDtos, HttpStatus.OK);
    }

    @GetMapping(GET_DISLOCATION_TYPE)
    public ResponseEntity<DislocationTypeDto> getDislocationTypeById(@PathVariable("id") short id) throws EntityNotFoundException {
        DislocationTypeDto dislocationTypeDto = dislocationTypeService.getDislocationTypeByIdAsDto(id);
        return new ResponseEntity<>(dislocationTypeDto, HttpStatus.OK);
    }

    @PostMapping(POST_DISLOCATION_TYPE)
    public ResponseEntity<DislocationTypeDto> createDislocationType(@RequestBody DislocationTypeDto dislocationTypeDto) {
        return new ResponseEntity<>(dislocationTypeService.createDislocationType(dislocationTypeDto), HttpStatus.OK);
    }

    @PutMapping(PUT_DISLOCATION_TYPE)
    public ResponseEntity<DislocationTypeDto> updateDislocationTypeById(@PathVariable("id") short id, @RequestBody DislocationTypeDto dislocationTypeDto) throws EntityNotFoundException {
        return new ResponseEntity<>(dislocationTypeService.updateDislocationTypeById(id, dislocationTypeDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_DISLOCATION_TYPE)
    public ResponseEntity<DislocationTypeDto> deleteDislocationTypeById(@PathVariable("id") short id) throws EntityNotFoundException {
        DislocationTypeDto dislocationTypeDto = dislocationTypeService.deleteDislocationTypeById(id);
        return new ResponseEntity<>(dislocationTypeDto, HttpStatus.OK);
    }

}
