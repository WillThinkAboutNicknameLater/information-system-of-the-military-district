package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.MilitaryFormationDto;
import ru.nsu.fit.militarysystem.dto.MilitaryManDto;
import ru.nsu.fit.militarysystem.dto.MilitarySpecialtyDto;
import ru.nsu.fit.militarysystem.filter.MilitaryManSearchFilter;
import ru.nsu.fit.militarysystem.service.MilitaryManService;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.util.RequestObjectParam;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class MilitaryManController {
    private final MilitaryManService militaryManService;

    private static final String GET_MILITARY_MEN = "/military-men";

    private static final String GET_MILITARY_MEN_BY_SEARCH_FILTER = "/military-men/search";

    private static final String GET_MILITARY_MAN = "/military-men/{id}";

    private static final String GET_SUBORDINATES = "/military-men/{id}/subordinates";

    private static final String GET_SPECIALTIES = "/military-men/{id}/military-specialties";

    private static final String GET_MILITARY_FORMATIONS = "/military-men/{id}/military-formations";

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
    public ResponseEntity<Page<MilitaryManDto>> getAllMilitaryMenByFilter(@RequestObjectParam MilitaryManSearchFilter militaryManSearchFilter)
            throws EntityNotFoundException {
        Page<MilitaryManDto> militaryManDtos = militaryManService.getAllMilitaryMenByFilterAsDtos(militaryManSearchFilter);
        return new ResponseEntity<>(militaryManDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_MAN)
    public ResponseEntity<MilitaryManDto> getMilitaryManById(@PathVariable("id") int id) throws EntityNotFoundException {
        MilitaryManDto militaryManDto = militaryManService.getMilitaryManByIdAsDto(id);
        return new ResponseEntity<>(militaryManDto, HttpStatus.OK);
    }

    @GetMapping(GET_SUBORDINATES)
    public ResponseEntity<List<MilitaryManDto>> getSubordinatesById(@PathVariable("id") int id) throws EntityNotFoundException {
        List<MilitaryManDto> militaryManDtos = militaryManService.getSubordinatesByIdAsDtos(id);
        return new ResponseEntity<>(militaryManDtos, HttpStatus.OK);
    }

    @GetMapping(GET_SPECIALTIES)
    public ResponseEntity<List<MilitarySpecialtyDto>> getMilitarySpecialtiesById(@PathVariable("id") int id) throws EntityNotFoundException {
        List<MilitarySpecialtyDto> militarySpecialtyDtos = militaryManService.getMilitarySpecialtiesByIdAsDtos(id);
        return new ResponseEntity<>(militarySpecialtyDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_FORMATIONS)
    public ResponseEntity<List<MilitaryFormationDto>> getMilitaryFormationsById(@PathVariable("id") int id) throws EntityNotFoundException {
        List<MilitaryFormationDto> militaryFormationDtos = militaryManService.getMilitaryFormationsByIdAsDtos(id);
        return new ResponseEntity<>(militaryFormationDtos, HttpStatus.OK);
    }

    @PostMapping(POST_MILITARY_MAN)
    public ResponseEntity<MilitaryManDto> createMilitaryMan(@RequestBody MilitaryManDto militaryManDto) throws EntityAlreadyExistsException {
        return new ResponseEntity<>(militaryManService.createMilitaryMan(militaryManDto), HttpStatus.CREATED);
    }

    @PutMapping(PUT_MILITARY_MAN)
    public ResponseEntity<MilitaryManDto> updateMilitaryManById(@PathVariable("id") int id, @RequestBody MilitaryManDto militaryManDto)
            throws EntityNotFoundException {
        return new ResponseEntity<>(militaryManService.updateMilitaryManById(id, militaryManDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_MILITARY_MAN)
    public ResponseEntity<MilitaryManDto> deleteMilitaryManById(@PathVariable("id") int id) throws EntityNotFoundException {
        MilitaryManDto militaryManDto = militaryManService.deleteMilitaryManById(id);
        return new ResponseEntity<>(militaryManDto, HttpStatus.OK);
    }

}
