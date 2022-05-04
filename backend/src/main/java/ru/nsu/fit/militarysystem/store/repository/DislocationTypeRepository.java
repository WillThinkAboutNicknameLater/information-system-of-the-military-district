package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.nsu.fit.militarysystem.store.entity.DislocationType;

import java.util.Optional;

public interface DislocationTypeRepository extends JpaRepository<DislocationType, Short> {
    Optional<DislocationType> findByName(@NonNull String name);
}
