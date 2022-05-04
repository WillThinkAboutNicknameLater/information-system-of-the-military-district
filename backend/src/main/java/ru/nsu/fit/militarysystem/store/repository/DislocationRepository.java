package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.Dislocation;

public interface DislocationRepository extends JpaRepository<Dislocation, Integer> {
}
