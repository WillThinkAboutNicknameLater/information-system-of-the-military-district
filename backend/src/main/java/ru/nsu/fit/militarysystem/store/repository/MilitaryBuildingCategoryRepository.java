package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.MilitaryBuildingCategory;

import java.util.Optional;

public interface MilitaryBuildingCategoryRepository extends JpaRepository<MilitaryBuildingCategory, Short> {
    Optional<MilitaryBuildingCategory> findByName(String name);

    Page<MilitaryBuildingCategory> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
