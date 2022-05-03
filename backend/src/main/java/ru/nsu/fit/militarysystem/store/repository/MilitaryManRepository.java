package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.MilitaryMan;

public interface MilitaryManRepository extends JpaRepository<MilitaryMan, Integer> {
}
