package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.*;
import ru.nsu.fit.militarysystem.filter.MilitaryFormationSearchFilter;
import ru.nsu.fit.militarysystem.service.MilitaryFormationService;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.util.RequestObjectParam;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class MilitaryFormationController {
    private final MilitaryFormationService militaryFormationService;

    private static final String GET_MILITARY_FORMATIONS = "/military-formations";

    private static final String GET_MILITARY_FORMATIONS_BY_SEARCH_FILTER = "/military-formations/search";

    private static final String GET_MILITARY_FORMATION = "/military-formations/{id}";

    private static final String GET_SUBORDINATES = "/military-formations/{id}/subordinates";

    private static final String GET_DISLOCATIONS = "/military-formations/{id}/dislocations";

    private static final String GET_MILITARY_MEN = "/military-formations/{id}/military-men";

    private static final String GET_COMBAT_VEHICLES = "/military-formations/{id}/combat-vehicles";

    private static final String GET_ARMAMENTS = "/military-formations/{id}/armaments";

    private static final String GET_MILITARY_BUILDINGS = "/military-formations/{id}/military-buildings";

    private static final String POST_MILITARY_FORMATION = "/military-formations";

    private static final String PUT_MILITARY_FORMATION = "/military-formations/{id}";

    private static final String DELETE_MILITARY_FORMATION = "/military-formations/{id}";

    public MilitaryFormationController(MilitaryFormationService militaryFormationService) {
        this.militaryFormationService = militaryFormationService;
    }

    @GetMapping(GET_MILITARY_FORMATIONS)
    public ResponseEntity<List<MilitaryFormationDto>> getAllMilitaryFormations() {
        List<MilitaryFormationDto> militaryFormationDtos = militaryFormationService.getAllMilitaryFormationsAsDtos();
        return new ResponseEntity<>(militaryFormationDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_FORMATIONS_BY_SEARCH_FILTER)
    public ResponseEntity<Page<MilitaryFormationDto>> getAllMilitaryFormationsByFilter(@RequestObjectParam MilitaryFormationSearchFilter militaryFormationSearchFilter)
            throws EntityNotFoundException {
        Page<MilitaryFormationDto> militaryFormationDtos =
                militaryFormationService.getAllMilitaryFormationsByFilterAsDtos(militaryFormationSearchFilter);
        return new ResponseEntity<>(militaryFormationDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_FORMATION)
    public ResponseEntity<MilitaryFormationDto> getMilitaryFormationById(@PathVariable("id") int id) throws EntityNotFoundException {
        MilitaryFormationDto militaryFormationDto = militaryFormationService.getMilitaryFormationByIdAsDto(id);
        return new ResponseEntity<>(militaryFormationDto, HttpStatus.OK);
    }

    @GetMapping(GET_SUBORDINATES)
    public ResponseEntity<List<MilitaryFormationDto>> getSubordinatesById(@PathVariable("id") int id) throws EntityNotFoundException {
        List<MilitaryFormationDto> militaryFormationDtos = militaryFormationService.getSubordinatesByIdAsDtos(id);
        return new ResponseEntity<>(militaryFormationDtos, HttpStatus.OK);
    }

    @GetMapping(GET_DISLOCATIONS)
    public ResponseEntity<List<DislocationDto>> getDislocationsById(@PathVariable("id") int id) throws EntityNotFoundException {
        List<DislocationDto> dislocationDtos = militaryFormationService.getDislocationsByIdAsDtos(id);
        return new ResponseEntity<>(dislocationDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_MEN)
    public ResponseEntity<List<MilitaryManDto>> getMilitaryMenById(@PathVariable("id") int id) throws EntityNotFoundException {
        List<MilitaryManDto> militaryManDtos = militaryFormationService.getMilitaryMenByIdAsDtos(id);
        return new ResponseEntity<>(militaryManDtos, HttpStatus.OK);
    }

    @GetMapping(GET_COMBAT_VEHICLES)
    public ResponseEntity<List<CombatVehicleDto>> getCombatVehiclesById(@PathVariable("id") int id) throws EntityNotFoundException {
        List<CombatVehicleDto> combatVehicleDtos = militaryFormationService.getCombatVehiclesByIdAsDtos(id);
        return new ResponseEntity<>(combatVehicleDtos, HttpStatus.OK);
    }

    @GetMapping(GET_ARMAMENTS)
    public ResponseEntity<List<ArmamentDto>> getArmamentsById(@PathVariable("id") int id) throws EntityNotFoundException {
        List<ArmamentDto> armamentDtos = militaryFormationService.getArmamentsByIdAsDtos(id);
        return new ResponseEntity<>(armamentDtos, HttpStatus.OK);
    }

    @GetMapping(GET_MILITARY_BUILDINGS)
    public ResponseEntity<List<MilitaryBuildingDto>> getMilitaryBuildingsById(@PathVariable("id") int id) throws EntityNotFoundException {
        List<MilitaryBuildingDto> militaryBuildingDtos = militaryFormationService.getMilitaryBuildingsByIdAsDtos(id);
        return new ResponseEntity<>(militaryBuildingDtos, HttpStatus.OK);
    }

    @PostMapping(POST_MILITARY_FORMATION)
    public ResponseEntity<MilitaryFormationDto> createMilitaryFormation(@RequestBody MilitaryFormationDto militaryFormationDto)
            throws EntityAlreadyExistsException {
        return new ResponseEntity<>(militaryFormationService.createMilitaryFormation(militaryFormationDto), HttpStatus.CREATED);
    }

    @PutMapping(PUT_MILITARY_FORMATION)
    public ResponseEntity<MilitaryFormationDto> updateMilitaryFormationById(@PathVariable("id") int id,
                                                                            @RequestBody MilitaryFormationDto militaryFormationDto)
            throws EntityNotFoundException {
        return new ResponseEntity<>(militaryFormationService.updateMilitaryFormationById(id, militaryFormationDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_MILITARY_FORMATION)
    public ResponseEntity<MilitaryFormationDto> deleteMilitaryFormationById(@PathVariable("id") int id) throws EntityNotFoundException {
        MilitaryFormationDto militaryFormationDto = militaryFormationService.deleteMilitaryFormationById(id);
        return new ResponseEntity<>(militaryFormationDto, HttpStatus.OK);
    }

}
