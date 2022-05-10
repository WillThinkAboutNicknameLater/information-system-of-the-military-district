package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.CombatVehicleGroupDto;
import ru.nsu.fit.militarysystem.filter.CombatVehicleGroupSearchFilter;
import ru.nsu.fit.militarysystem.service.CombatVehicleGroupService;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.util.RequestObjectParam;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CombatVehicleGroupController {
    private final CombatVehicleGroupService combatVehicleGroupService;

    private static final String GET_COMBAT_VEHICLE_GROUPS = "/combat-vehicle-groups";

    private static final String GET_COMBAT_VEHICLE_GROUPS_BY_SEARCH_FILTER = "/combat-vehicle-groups/search";

    private static final String GET_COMBAT_VEHICLE_GROUP = "/combat-vehicle-groups/{id}";

    private static final String POST_COMBAT_VEHICLE_GROUP = "/combat-vehicle-groups";

    private static final String PUT_COMBAT_VEHICLE_GROUP = "/combat-vehicle-groups/{id}";

    private static final String DELETE_COMBAT_VEHICLE_GROUP = "/combat-vehicle-groups/{id}";

    public CombatVehicleGroupController(CombatVehicleGroupService combatVehicleGroupService) {
        this.combatVehicleGroupService = combatVehicleGroupService;
    }

    @GetMapping(GET_COMBAT_VEHICLE_GROUPS)
    public ResponseEntity<List<CombatVehicleGroupDto>> getAllCombatVehicleGroups() {
        List<CombatVehicleGroupDto> combatVehicleGroupDtos = combatVehicleGroupService.getAllCombatVehicleGroupsAsDtos();
        return new ResponseEntity<>(combatVehicleGroupDtos, HttpStatus.OK);
    }

    @GetMapping(GET_COMBAT_VEHICLE_GROUPS_BY_SEARCH_FILTER)
    public ResponseEntity<Page<CombatVehicleGroupDto>> getAllCombatVehicleGroupsByFilter(@RequestObjectParam CombatVehicleGroupSearchFilter combatVehicleGroupSearchFilter)
            throws EntityNotFoundException {
        Page<CombatVehicleGroupDto> combatVehicleGroupDtos =
                combatVehicleGroupService.getAllCombatVehicleGroupsByFilterAsDtos(combatVehicleGroupSearchFilter);
        return new ResponseEntity<>(combatVehicleGroupDtos, HttpStatus.OK);
    }

    @GetMapping(GET_COMBAT_VEHICLE_GROUP)
    public ResponseEntity<CombatVehicleGroupDto> getCombatVehicleGroupById(@PathVariable("id") short id) throws EntityNotFoundException {
        CombatVehicleGroupDto combatVehicleGroupDto = combatVehicleGroupService.getCombatVehicleGroupByIdAsDto(id);
        return new ResponseEntity<>(combatVehicleGroupDto, HttpStatus.OK);
    }

    @PostMapping(POST_COMBAT_VEHICLE_GROUP)
    public ResponseEntity<CombatVehicleGroupDto> createCombatVehicleGroup(@RequestBody CombatVehicleGroupDto combatVehicleGroupDto)
            throws EntityAlreadyExistsException {
        return new ResponseEntity<>(combatVehicleGroupService.createCombatVehicleGroup(combatVehicleGroupDto), HttpStatus.CREATED);
    }

    @PutMapping(PUT_COMBAT_VEHICLE_GROUP)
    public ResponseEntity<CombatVehicleGroupDto> updateCombatVehicleGroupById(@PathVariable("id") short id,
                                                                              @RequestBody CombatVehicleGroupDto combatVehicleGroupDto)
            throws EntityNotFoundException {
        return new ResponseEntity<>(combatVehicleGroupService.updateCombatVehicleGroupById(id, combatVehicleGroupDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_COMBAT_VEHICLE_GROUP)
    public ResponseEntity<CombatVehicleGroupDto> deleteCombatVehicleGroupById(@PathVariable("id") short id) throws EntityNotFoundException {
        CombatVehicleGroupDto combatVehicleGroupDto = combatVehicleGroupService.deleteCombatVehicleGroupById(id);
        return new ResponseEntity<>(combatVehicleGroupDto, HttpStatus.OK);
    }

}
