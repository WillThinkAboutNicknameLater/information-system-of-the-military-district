package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.nsu.fit.militarysystem.store.entity.RankCategory;

import java.util.Optional;

public interface RankCategoryRepository extends JpaRepository<RankCategory, Short> {
    Optional<RankCategory> findByName(@NonNull String name);
}
