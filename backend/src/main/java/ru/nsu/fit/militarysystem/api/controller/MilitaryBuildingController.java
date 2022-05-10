package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.MilitaryBuildingDto;
import ru.nsu.fit.militarysystem.filter.MilitaryBuildingSearchFilter;
import ru.nsu.fit.militarysystem.service.MilitaryBuildingService;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.util.RequestObjectParam;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class MilitaryBuildingController {
    private final MilitaryBuildingService militaryBuildingService;

    private static final String GET_MILITARY_BUILDINGS = "/military-buildings";

    private static final String GET_MILITARY_BUILDINGS_BY_SEARCH_FILTER = "/military-buildings/search";

    private static final String GET_MILITARY_BUILDING = "/military-buildings/{id}";

    private static final String POST_MILITARY_BUILDING = "/military-buildings";

    private static final String PUT_MILITARY_BUILDING = "/military-buildings/{id}";

    private static final String DELETE_MILITARY_BUILDING = "/military-buildings/{id}";

    public MilitaryBuildingController(MilitaryBuildingService militaryBuildingService) {
        this.militaryBuildingService = militaryBuildingService;
    }

    @GetMapping(GET_MILITARY_BUILDINGS)
    public ResponseEntity<List<MilitaryBuildingDto>> getAllMilitaryBuildings() {
        List<MilitaryBuildingDto> militaryBuildingDtos = militaryBuildingService.getAllMilitaryBuildingsAsDtos();
        return new ResponseEntity<>(militaryBuildingDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_BUILDINGS_BY_SEARCH_FILTER)
    public ResponseEntity<Page<MilitaryBuildingDto>> getAllMilitaryBuildingsByFilter(@RequestObjectParam MilitaryBuildingSearchFilter militaryBuildingSearchFilter)
            throws EntityNotFoundException {
        Page<MilitaryBuildingDto> militaryBuildingDtos = militaryBuildingService.getAllMilitaryBuildingsByFilterAsDtos(militaryBuildingSearchFilter);
        return new ResponseEntity<>(militaryBuildingDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_BUILDING)
    public ResponseEntity<MilitaryBuildingDto> getMilitaryBuildingById(@PathVariable("id") int id) throws EntityNotFoundException {
        MilitaryBuildingDto militaryBuildingDto = militaryBuildingService.getMilitaryBuildingByIdAsDto(id);
        return new ResponseEntity<>(militaryBuildingDto, HttpStatus.OK);
    }

    @PostMapping(POST_MILITARY_BUILDING)
    public ResponseEntity<MilitaryBuildingDto> createMilitaryBuilding(@RequestBody MilitaryBuildingDto militaryBuildingDto)
            throws EntityAlreadyExistsException {
        return new ResponseEntity<>(militaryBuildingService.createMilitaryBuilding(militaryBuildingDto), HttpStatus.CREATED);
    }

    @PutMapping(PUT_MILITARY_BUILDING)
    public ResponseEntity<MilitaryBuildingDto> updateMilitaryBuildingById(@PathVariable("id") int id,
                                                                          @RequestBody MilitaryBuildingDto militaryBuildingDto)
            throws EntityNotFoundException {
        return new ResponseEntity<>(militaryBuildingService.updateMilitaryBuildingById(id, militaryBuildingDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_MILITARY_BUILDING)
    public ResponseEntity<MilitaryBuildingDto> deleteMilitaryBuildingById(@PathVariable("id") int id) throws EntityNotFoundException {
        MilitaryBuildingDto militaryBuildingDto = militaryBuildingService.deleteMilitaryBuildingById(id);
        return new ResponseEntity<>(militaryBuildingDto, HttpStatus.OK);
    }

}
