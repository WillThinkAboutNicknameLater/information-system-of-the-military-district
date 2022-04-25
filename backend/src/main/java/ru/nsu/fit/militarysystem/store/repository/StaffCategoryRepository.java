package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.nsu.fit.militarysystem.store.entity.StaffCategory;

import java.util.Optional;

public interface StaffCategoryRepository extends JpaRepository<StaffCategory, Short> {
    Optional<StaffCategory> findByName(@NonNull String name);
}
