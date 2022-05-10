package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.CombatVehicleGroup;

import java.util.Optional;

public interface CombatVehicleGroupRepository extends JpaRepository<CombatVehicleGroup, Short> {
    Optional<CombatVehicleGroup> findByName(String name);

    Page<CombatVehicleGroup> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
