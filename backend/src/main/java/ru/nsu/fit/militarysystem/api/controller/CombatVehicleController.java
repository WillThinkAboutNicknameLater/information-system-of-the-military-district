package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.CombatVehicleDto;
import ru.nsu.fit.militarysystem.filter.CombatVehicleSearchFilter;
import ru.nsu.fit.militarysystem.service.CombatVehicleService;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.util.RequestObjectParam;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CombatVehicleController {
    private final CombatVehicleService combatVehicleService;

    private static final String GET_COMBAT_VEHICLES = "/combat-vehicles";

    private static final String GET_COMBAT_VEHICLES_BY_SEARCH_FILTER = "/combat-vehicles/search";

    private static final String GET_COMBAT_VEHICLE = "/combat-vehicles/{id}";

    private static final String POST_COMBAT_VEHICLE = "/combat-vehicles";

    private static final String PUT_COMBAT_VEHICLE = "/combat-vehicles/{id}";

    private static final String DELETE_COMBAT_VEHICLE = "/combat-vehicles/{id}";

    public CombatVehicleController(CombatVehicleService combatVehicleService) {
        this.combatVehicleService = combatVehicleService;
    }

    @GetMapping(GET_COMBAT_VEHICLES)
    public ResponseEntity<List<CombatVehicleDto>> getAllCombatVehicles() {
        List<CombatVehicleDto> combatVehicleDtos = combatVehicleService.getAllCombatVehiclesAsDtos();
        return new ResponseEntity<>(combatVehicleDtos, HttpStatus.OK);
    }

    @GetMapping(GET_COMBAT_VEHICLES_BY_SEARCH_FILTER)
    public ResponseEntity<Page<CombatVehicleDto>> getAllCombatVehiclesByFilter(@RequestObjectParam CombatVehicleSearchFilter combatVehicleSearchFilter)
            throws EntityNotFoundException {
        Page<CombatVehicleDto> combatVehicleDtos = combatVehicleService.getAllCombatVehiclesByFilterAsDtos(combatVehicleSearchFilter);
        return new ResponseEntity<>(combatVehicleDtos, HttpStatus.OK);
    }

    @GetMapping(GET_COMBAT_VEHICLE)
    public ResponseEntity<CombatVehicleDto> getCombatVehicleById(@PathVariable("id") int id) throws EntityNotFoundException {
        CombatVehicleDto combatVehicleDto = combatVehicleService.getCombatVehicleByIdAsDto(id);
        return new ResponseEntity<>(combatVehicleDto, HttpStatus.OK);
    }

    @PostMapping(POST_COMBAT_VEHICLE)
    public ResponseEntity<CombatVehicleDto> createCombatVehicle(@RequestBody CombatVehicleDto combatVehicleDto) throws EntityAlreadyExistsException {
        return new ResponseEntity<>(combatVehicleService.createCombatVehicle(combatVehicleDto), HttpStatus.CREATED);
    }

    @PutMapping(PUT_COMBAT_VEHICLE)
    public ResponseEntity<CombatVehicleDto> updateCombatVehicleById(@PathVariable("id") int id, @RequestBody CombatVehicleDto combatVehicleDto)
            throws EntityNotFoundException {
        return new ResponseEntity<>(combatVehicleService.updateCombatVehicleById(id, combatVehicleDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_COMBAT_VEHICLE)
    public ResponseEntity<CombatVehicleDto> deleteCombatVehicleById(@PathVariable("id") int id) throws EntityNotFoundException {
        CombatVehicleDto combatVehicleDto = combatVehicleService.deleteCombatVehicleById(id);
        return new ResponseEntity<>(combatVehicleDto, HttpStatus.OK);
    }

}
