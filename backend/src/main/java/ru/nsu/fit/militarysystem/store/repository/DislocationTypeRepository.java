package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.DislocationType;

import java.util.Optional;

public interface DislocationTypeRepository extends JpaRepository<DislocationType, Short> {
    Optional<DislocationType> findByName(String name);

    Page<DislocationType> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
