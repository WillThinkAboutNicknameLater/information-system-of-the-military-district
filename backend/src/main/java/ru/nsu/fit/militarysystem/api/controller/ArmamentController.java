package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.ArmamentDto;
import ru.nsu.fit.militarysystem.filter.ArmamentSearchFilter;
import ru.nsu.fit.militarysystem.service.ArmamentService;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.util.RequestObjectParam;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class ArmamentController {
    private final ArmamentService armamentService;

    private static final String GET_ARMAMENTS = "/armaments";

    private static final String GET_ARMAMENTS_BY_SEARCH_FILTER = "/armaments/search";

    private static final String GET_ARMAMENT = "/armaments/{id}";

    private static final String POST_ARMAMENT = "/armaments";

    private static final String PUT_ARMAMENT = "/armaments/{id}";

    private static final String DELETE_ARMAMENT = "/armaments/{id}";

    public ArmamentController(ArmamentService armamentService) {
        this.armamentService = armamentService;
    }

    @GetMapping(GET_ARMAMENTS)
    public ResponseEntity<List<ArmamentDto>> getAllArmaments() {
        List<ArmamentDto> armamentDtos = armamentService.getAllArmamentsAsDtos();
        return new ResponseEntity<>(armamentDtos, HttpStatus.OK);
    }

    @GetMapping(GET_ARMAMENTS_BY_SEARCH_FILTER)
    public ResponseEntity<Page<ArmamentDto>> getAllArmamentsByFilter(@RequestObjectParam ArmamentSearchFilter armamentSearchFilter)
            throws EntityNotFoundException {
        Page<ArmamentDto> armamentDtos = armamentService.getAllArmamentsByFilterAsDtos(armamentSearchFilter);
        return new ResponseEntity<>(armamentDtos, HttpStatus.OK);
    }

    @GetMapping(GET_ARMAMENT)
    public ResponseEntity<ArmamentDto> getArmamentById(@PathVariable("id") int id) throws EntityNotFoundException {
        ArmamentDto armamentDto = armamentService.getArmamentByIdAsDto(id);
        return new ResponseEntity<>(armamentDto, HttpStatus.OK);
    }

    @PostMapping(POST_ARMAMENT)
    public ResponseEntity<ArmamentDto> createArmament(@RequestBody ArmamentDto armamentDto) throws EntityAlreadyExistsException {
        return new ResponseEntity<>(armamentService.createArmament(armamentDto), HttpStatus.CREATED);
    }

    @PutMapping(PUT_ARMAMENT)
    public ResponseEntity<ArmamentDto> updateArmamentById(@PathVariable("id") int id,
                                                          @RequestBody ArmamentDto armamentDto) throws EntityNotFoundException {
        return new ResponseEntity<>(armamentService.updateArmamentById(id, armamentDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_ARMAMENT)
    public ResponseEntity<ArmamentDto> deleteArmamentById(@PathVariable("id") int id) throws EntityNotFoundException {
        ArmamentDto armamentDto = armamentService.deleteArmamentById(id);
        return new ResponseEntity<>(armamentDto, HttpStatus.OK);
    }

}
