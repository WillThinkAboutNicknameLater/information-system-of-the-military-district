package ru.nsu.fit.militarysystem.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.fit.militarysystem.store.entity.Rank;

public interface RankRepository extends JpaRepository<Rank, Short> {
}
