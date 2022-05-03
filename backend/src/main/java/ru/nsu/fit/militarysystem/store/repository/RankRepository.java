package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.nsu.fit.militarysystem.store.entity.Rank;

import java.util.Optional;

public interface RankRepository extends JpaRepository<Rank, Short> {
    Optional<Rank> findByName(@NonNull String name);
}
