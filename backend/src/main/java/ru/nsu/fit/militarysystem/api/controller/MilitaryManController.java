package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.MilitaryManDto;
import ru.nsu.fit.militarysystem.dto.MilitarySpecialtyDto;
import ru.nsu.fit.militarysystem.service.MilitaryManService;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.MilitaryManSearchFilter;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class MilitaryManController {
    private final MilitaryManService militaryManService;

    private static final String GET_MILITARY_MEN = "/military-men";

    private static final String GET_MILITARY_MEN_BY_SEARCH_FILTER = "/military-men/search";

    private static final String GET_MILITARY_MAN = "/military-men/{id}";

    private static final String GET_MILITARY_MAN_SPECIALTIES = "/military-men/{id}/specialties";

    private static final String POST_MILITARY_MAN = "/military-men";

    private static final String PUT_MILITARY_MAN = "/military-men/{id}";

    private static final String DELETE_MILITARY_MAN = "/military-men/{id}";

    public MilitaryManController(MilitaryManService militaryManService) {
        this.militaryManService = militaryManService;
    }

    @GetMapping(GET_MILITARY_MEN)
    public ResponseEntity<List<MilitaryManDto>> getAllMilitaryMen() {
        List<MilitaryManDto> militaryManDtos = militaryManService.getAllMilitaryMenAsDtos();
        return new ResponseEntity<>(militaryManDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_MEN_BY_SEARCH_FILTER)
    public ResponseEntity<Page<MilitaryManDto>> getAllMilitaryMenByFilter(@RequestBody(required = false) MilitaryManSearchFilter militaryManSearchFilter) throws EntityNotFoundException {
        Page<MilitaryManDto> militaryManDtos = militaryManService.getAllMilitaryMenByFilterAsDtos(militaryManSearchFilter);
        return new ResponseEntity<>(militaryManDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_MAN)
    public ResponseEntity<MilitaryManDto> getMilitaryManById(@PathVariable("id") short id) throws EntityNotFoundException {
        MilitaryManDto militaryManDto = militaryManService.getMilitaryManByIdAsDto(id);
        return new ResponseEntity<>(militaryManDto, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_MAN_SPECIALTIES)
    public ResponseEntity<List<MilitarySpecialtyDto>> getMilitaryManSpecialtiesById(@PathVariable("id") short id) throws EntityNotFoundException {
        List<MilitarySpecialtyDto> militarySpecialtyDtos = militaryManService.getMilitaryManSpecialtiesByIdAsDtos(id);
        return new ResponseEntity<>(militarySpecialtyDtos, HttpStatus.OK);
    }

    @PostMapping(POST_MILITARY_MAN)
    public ResponseEntity<MilitaryManDto> createMilitaryMan(@RequestBody MilitaryManDto militaryManDto) {
        return new ResponseEntity<>(militaryManService.createMilitaryMan(militaryManDto), HttpStatus.OK);
    }

    @PutMapping(PUT_MILITARY_MAN)
    public ResponseEntity<MilitaryManDto> updateMilitaryManById(@PathVariable("id") short id, @RequestBody MilitaryManDto militaryManDto) throws EntityNotFoundException {
        return new ResponseEntity<>(militaryManService.updateMilitaryManById(id, militaryManDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_MILITARY_MAN)
    public ResponseEntity<MilitaryManDto> deleteMilitaryManById(@PathVariable("id") short id) throws EntityNotFoundException {
        MilitaryManDto militaryManDto = militaryManService.deleteMilitaryManById(id);
        return new ResponseEntity<>(militaryManDto, HttpStatus.OK);
    }

}
