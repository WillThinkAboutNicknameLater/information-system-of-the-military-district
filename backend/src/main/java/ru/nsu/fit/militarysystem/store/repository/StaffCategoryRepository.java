package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.StaffCategory;

import java.util.Optional;

public interface StaffCategoryRepository extends JpaRepository<StaffCategory, Short> {
    Optional<StaffCategory> findByName(String name);

    Page<StaffCategory> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
