package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Short> {
}
