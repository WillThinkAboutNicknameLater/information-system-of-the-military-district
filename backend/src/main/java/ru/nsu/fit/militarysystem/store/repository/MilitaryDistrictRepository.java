package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.MilitaryDistrict;

import java.util.Optional;

public interface MilitaryDistrictRepository extends JpaRepository<MilitaryDistrict, Short> {
    Optional<MilitaryDistrict> findByName(String name);

    Page<MilitaryDistrict> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
