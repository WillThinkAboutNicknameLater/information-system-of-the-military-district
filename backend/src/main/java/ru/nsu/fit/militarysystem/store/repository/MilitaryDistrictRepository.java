package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.nsu.fit.militarysystem.store.entity.MilitaryDistrict;

import java.util.Optional;

public interface MilitaryDistrictRepository extends JpaRepository<MilitaryDistrict, Short> {
    Optional<MilitaryDistrict> findByName(@NonNull String name);
}
