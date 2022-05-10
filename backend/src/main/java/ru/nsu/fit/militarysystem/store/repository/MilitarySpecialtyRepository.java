package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.MilitarySpecialty;

import java.util.Optional;

public interface MilitarySpecialtyRepository extends JpaRepository<MilitarySpecialty, Short> {
    Optional<MilitarySpecialty> findByName(String name);

    Page<MilitarySpecialty> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
