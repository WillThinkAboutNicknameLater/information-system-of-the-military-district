package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.nsu.fit.militarysystem.store.entity.MilitaryFormationType;

import java.util.Optional;

public interface MilitaryFormationTypeRepository extends JpaRepository<MilitaryFormationType, Short> {
    Optional<MilitaryFormationType> findByName(@NonNull String name);
}
