package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.MilitaryDistrictDto;
import ru.nsu.fit.militarysystem.service.MilitaryDistrictService;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.MilitaryDistrictSearchFilter;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class MilitaryDistrictController {
    private final MilitaryDistrictService militaryDistrictService;

    private static final String GET_MILITARY_DISTRICTS = "/military-districts";

    private static final String GET_MILITARY_DISTRICTS_BY_SEARCH_FILTER = "/military-districts/search";

    private static final String GET_MILITARY_DISTRICT = "/military-districts/{id}";

    private static final String POST_MILITARY_DISTRICT = "/military-districts";

    private static final String PUT_MILITARY_DISTRICT = "/military-districts/{id}";

    private static final String DELETE_MILITARY_DISTRICT = "/military-districts/{id}";

    public MilitaryDistrictController(MilitaryDistrictService militaryDistrictService) {
        this.militaryDistrictService = militaryDistrictService;
    }

    @GetMapping(GET_MILITARY_DISTRICTS)
    public ResponseEntity<List<MilitaryDistrictDto>> getAllMilitaryDistricts() {
        List<MilitaryDistrictDto> militaryDistrictDtos = militaryDistrictService.getAllMilitaryDistrictsAsDtos();
        return new ResponseEntity<>(militaryDistrictDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_DISTRICTS_BY_SEARCH_FILTER)
    public ResponseEntity<Page<MilitaryDistrictDto>> getAllMilitaryDistrictsByFilter(@RequestBody(required = false) MilitaryDistrictSearchFilter militaryDistrictSearchFilter) throws EntityNotFoundException {
        Page<MilitaryDistrictDto> militaryDistrictDtos = militaryDistrictService.getAllMilitaryDistrictsByFilterAsDtos(militaryDistrictSearchFilter);
        return new ResponseEntity<>(militaryDistrictDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_DISTRICT)
    public ResponseEntity<MilitaryDistrictDto> getMilitaryDistrictById(@PathVariable("id") short id) throws EntityNotFoundException {
        MilitaryDistrictDto militaryDistrictDto = militaryDistrictService.getMilitaryDistrictByIdAsDto(id);
        return new ResponseEntity<>(militaryDistrictDto, HttpStatus.OK);
    }

    @PostMapping(POST_MILITARY_DISTRICT)
    public ResponseEntity<MilitaryDistrictDto> createMilitaryDistrict(@RequestBody MilitaryDistrictDto militaryDistrictDto) {
        return new ResponseEntity<>(militaryDistrictService.createMilitaryDistrict(militaryDistrictDto), HttpStatus.OK);
    }

    @PutMapping(PUT_MILITARY_DISTRICT)
    public ResponseEntity<MilitaryDistrictDto> updateMilitaryDistrictById(@PathVariable("id") short id, @RequestBody MilitaryDistrictDto militaryDistrictDto) throws EntityNotFoundException {
        return new ResponseEntity<>(militaryDistrictService.updateMilitaryDistrictById(id, militaryDistrictDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_MILITARY_DISTRICT)
    public ResponseEntity<MilitaryDistrictDto> deleteMilitaryDistrictById(@PathVariable("id") short id) throws EntityNotFoundException {
        MilitaryDistrictDto militaryDistrictDto = militaryDistrictService.deleteMilitaryDistrictById(id);
        return new ResponseEntity<>(militaryDistrictDto, HttpStatus.OK);
    }

}
