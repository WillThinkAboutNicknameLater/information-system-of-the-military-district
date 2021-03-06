package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.RankCategory;

import java.util.Optional;

public interface RankCategoryRepository extends JpaRepository<RankCategory, Short> {
    Optional<RankCategory> findByName(String name);

    Page<RankCategory> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
