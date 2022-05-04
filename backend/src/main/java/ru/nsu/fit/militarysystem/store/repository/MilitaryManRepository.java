package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.nsu.fit.militarysystem.store.entity.MilitaryMan;

import java.util.Optional;

public interface MilitaryManRepository extends JpaRepository<MilitaryMan, Integer> {
    Optional<MilitaryMan> findByIdentificationNumber(@NonNull String identificationNumber);
}
