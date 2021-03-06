package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.Subject;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Short> {
    Optional<Subject> findByName(String name);

    Page<Subject> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
