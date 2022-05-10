package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nsu.fit.militarysystem.store.entity.CombatVehicle;

import java.util.List;

public interface CombatVehicleRepository extends JpaRepository<CombatVehicle, Integer> {
    @Query(value = "SELECT * FROM find_all_combat_vehicles_by_filter(CAST(:searchName AS text), CAST(:combatVehicleCategoryIds AS smallint[]), CAST(:combatVehicleGroupIds AS smallint[]), CAST(:militaryFormationIds AS integer[]))",
           nativeQuery = true)
    Page<CombatVehicle> findAllByFilter(String searchName,
                                        String combatVehicleCategoryIds,
                                        String combatVehicleGroupIds,
                                        String militaryFormationIds,
                                        Pageable pageable);

    @Query(value = "SELECT * FROM find_all_combat_vehicles_by_military_formation_id(CAST(:id AS integer))",
           nativeQuery = true)
    List<CombatVehicle> findAllByMilitaryFormationId(int id);
}
