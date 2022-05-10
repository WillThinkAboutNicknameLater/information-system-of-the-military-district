package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.ArmamentCategory;

import java.util.Optional;

public interface ArmamentCategoryRepository extends JpaRepository<ArmamentCategory, Short> {
    Optional<ArmamentCategory> findByName(String name);

    Page<ArmamentCategory> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
