package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.MilitaryFormationTypeDto;
import ru.nsu.fit.militarysystem.service.MilitaryFormationTypeService;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.MilitaryFormationTypeSearchFilter;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class MilitaryFormationTypeController {
    private final MilitaryFormationTypeService militaryFormationTypeService;

    private static final String GET_MILITARY_FORMATION_TYPES= "/military-formation-types";

    private static final String GET_MILITARY_FORMATION_TYPES_BY_SEARCH_FILTER = "/military-formation-types/search";

    private static final String GET_MILITARY_FORMATION_TYPE = "/military-formation-types/{id}";

    private static final String POST_MILITARY_FORMATION_TYPE = "/military-formation-types";

    private static final String PUT_MILITARY_FORMATION_TYPE = "/military-formation-types/{id}";

    private static final String DELETE_MILITARY_FORMATION_TYPE = "/military-formation-types/{id}";

    public MilitaryFormationTypeController(MilitaryFormationTypeService militaryFormationTypeService) {
        this.militaryFormationTypeService = militaryFormationTypeService;
    }

    @GetMapping(GET_MILITARY_FORMATION_TYPES)
    public ResponseEntity<List<MilitaryFormationTypeDto>> getAllMilitaryFormationTypes() {
        List<MilitaryFormationTypeDto> militaryFormationTypeDtos = militaryFormationTypeService.getAllMilitaryFormationTypesAsDtos();
        return new ResponseEntity<>(militaryFormationTypeDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_FORMATION_TYPES_BY_SEARCH_FILTER)
    public ResponseEntity<Page<MilitaryFormationTypeDto>> getAllMilitaryFormationTypesByFilter(@RequestBody(required = false) MilitaryFormationTypeSearchFilter militaryFormationTypeSearchFilter) throws EntityNotFoundException {
        Page<MilitaryFormationTypeDto> militaryFormationTypeDtos = militaryFormationTypeService.getAllMilitaryFormationTypesByFilterAsDtos(militaryFormationTypeSearchFilter);
        return new ResponseEntity<>(militaryFormationTypeDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_FORMATION_TYPE)
    public ResponseEntity<MilitaryFormationTypeDto> getMilitaryFormationTypeById(@PathVariable("id") short id) throws EntityNotFoundException {
        MilitaryFormationTypeDto militaryFormationTypeDto = militaryFormationTypeService.getMilitaryFormationTypeByIdAsDto(id);
        return new ResponseEntity<>(militaryFormationTypeDto, HttpStatus.OK);
    }

    @PostMapping(POST_MILITARY_FORMATION_TYPE)
    public ResponseEntity<MilitaryFormationTypeDto> createMilitaryFormationType(@RequestBody MilitaryFormationTypeDto militaryFormationTypeDto) {
        return new ResponseEntity<>(militaryFormationTypeService.createMilitaryFormationType(militaryFormationTypeDto), HttpStatus.OK);
    }

    @PutMapping(PUT_MILITARY_FORMATION_TYPE)
    public ResponseEntity<MilitaryFormationTypeDto> updateMilitaryFormationTypeById(@PathVariable("id") short id, @RequestBody MilitaryFormationTypeDto militaryFormationTypeDto) throws EntityNotFoundException {
        return new ResponseEntity<>(militaryFormationTypeService.updateMilitaryFormationTypeById(id, militaryFormationTypeDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_MILITARY_FORMATION_TYPE)
    public ResponseEntity<MilitaryFormationTypeDto> deleteMilitaryFormationTypeById(@PathVariable("id") short id) throws EntityNotFoundException {
        MilitaryFormationTypeDto militaryFormationTypeDto = militaryFormationTypeService.deleteMilitaryFormationTypeById(id);
        return new ResponseEntity<>(militaryFormationTypeDto, HttpStatus.OK);
    }

}
