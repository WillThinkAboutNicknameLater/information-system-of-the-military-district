package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.MilitaryFormationType;

import java.util.Optional;

public interface MilitaryFormationTypeRepository extends JpaRepository<MilitaryFormationType, Short> {
    Optional<MilitaryFormationType> findByName(String name);

    Page<MilitaryFormationType> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
