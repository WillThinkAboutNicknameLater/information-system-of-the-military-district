package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.MilitarySpecialtyDto;
import ru.nsu.fit.militarysystem.service.MilitarySpecialtyService;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.MilitarySpecialtySearchFilter;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class MilitarySpecialtyController {
    private final MilitarySpecialtyService militarySpecialtyService;

    private static final String GET_MILITARY_SPECIALTIES = "/military-specialties";

    private static final String GET_MILITARY_SPECIALTIES_BY_SEARCH_FILTER = "/military-specialties/search";

    private static final String GET_MILITARY_SPECIALTY = "/military-specialties/{id}";

    private static final String POST_MILITARY_SPECIALTY = "/military-specialties";

    private static final String PUT_MILITARY_SPECIALTY = "/military-specialties/{id}";

    private static final String DELETE_MILITARY_SPECIALTY = "/military-specialties/{id}";

    public MilitarySpecialtyController(MilitarySpecialtyService militarySpecialtyService) {
        this.militarySpecialtyService = militarySpecialtyService;
    }

    @GetMapping(GET_MILITARY_SPECIALTIES)
    public ResponseEntity<List<MilitarySpecialtyDto>> getAllMilitarySpecialties() {
        List<MilitarySpecialtyDto> militarySpecialtyDtos = militarySpecialtyService.getAllMilitarySpecialtiesAsDtos();
        return new ResponseEntity<>(militarySpecialtyDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_SPECIALTIES_BY_SEARCH_FILTER)
    public ResponseEntity<Page<MilitarySpecialtyDto>> getAllMilitarySpecialtiesByFilter(@RequestBody(required = false) MilitarySpecialtySearchFilter militarySpecialtySearchFilter) throws EntityNotFoundException {
        Page<MilitarySpecialtyDto> militarySpecialtyDtos = militarySpecialtyService.getAllMilitarySpecialtiesByFilterAsDtos(militarySpecialtySearchFilter);
        return new ResponseEntity<>(militarySpecialtyDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_SPECIALTY)
    public ResponseEntity<MilitarySpecialtyDto> getMilitarySpecialtyById(@PathVariable("id") short id) throws EntityNotFoundException {
        MilitarySpecialtyDto militarySpecialtyDto = militarySpecialtyService.getMilitarySpecialtyByIdAsDto(id);
        return new ResponseEntity<>(militarySpecialtyDto, HttpStatus.OK);
    }

    @PostMapping(POST_MILITARY_SPECIALTY)
    public ResponseEntity<MilitarySpecialtyDto> createMilitarySpecialty(@RequestBody MilitarySpecialtyDto militarySpecialtyDto) {
        return new ResponseEntity<>(militarySpecialtyService.createMilitarySpecialty(militarySpecialtyDto), HttpStatus.OK);
    }

    @PutMapping(PUT_MILITARY_SPECIALTY)
    public ResponseEntity<MilitarySpecialtyDto> updateMilitarySpecialtyById(@PathVariable("id") short id, @RequestBody MilitarySpecialtyDto militarySpecialtyDto) throws EntityNotFoundException {
        return new ResponseEntity<>(militarySpecialtyService.updateMilitarySpecialtyById(id, militarySpecialtyDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_MILITARY_SPECIALTY)
    public ResponseEntity<MilitarySpecialtyDto> deleteMilitarySpecialtyById(@PathVariable("id") short id) throws EntityNotFoundException {
        MilitarySpecialtyDto militarySpecialtyDto = militarySpecialtyService.deleteMilitarySpecialtyById(id);
        return new ResponseEntity<>(militarySpecialtyDto, HttpStatus.OK);
    }

}
