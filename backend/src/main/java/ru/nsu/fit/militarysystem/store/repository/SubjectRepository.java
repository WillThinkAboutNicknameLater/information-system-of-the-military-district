package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.nsu.fit.militarysystem.store.entity.Subject;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Short> {
    Optional<Subject> findByName(@NonNull String name);
}
