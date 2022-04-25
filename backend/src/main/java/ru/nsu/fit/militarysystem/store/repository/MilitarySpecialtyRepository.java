package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.MilitarySpecialty;

public interface MilitarySpecialtyRepository extends JpaRepository<MilitarySpecialty, Short> {
}
