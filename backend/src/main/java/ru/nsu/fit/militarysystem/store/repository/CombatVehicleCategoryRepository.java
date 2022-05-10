package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nsu.fit.militarysystem.store.entity.CombatVehicleCategory;

import java.util.Optional;

public interface CombatVehicleCategoryRepository extends JpaRepository<CombatVehicleCategory, Short> {
    Optional<CombatVehicleCategory> findByName(String name);

    @Query(value = "SELECT * FROM find_all_combat_vehicle_categories_by_filter(CAST(:searchName AS text), CAST(:combatVehicleGroupIds AS smallint[]))",
           nativeQuery = true)
    Page<CombatVehicleCategory> findAllByFilter(String searchName, String combatVehicleGroupIds, Pageable pageable);
}
