package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.nsu.fit.militarysystem.store.entity.MilitarySpecialty;

import java.util.Optional;

public interface MilitarySpecialtyRepository extends JpaRepository<MilitarySpecialty, Short> {
    Optional<MilitarySpecialty> findByName(@NonNull String name);
}
