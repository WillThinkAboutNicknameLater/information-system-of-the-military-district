package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.RankDto;
import ru.nsu.fit.militarysystem.mapper.RankMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.RankSearchFilter;
import ru.nsu.fit.militarysystem.store.entity.Rank;
import ru.nsu.fit.militarysystem.store.repository.RankCriteriaRepository;
import ru.nsu.fit.militarysystem.store.repository.RankRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RankService {
    private final RankRepository rankRepository;

    private final RankCriteriaRepository rankCriteriaRepository;

    private final RankMapper rankMapper;

    public RankService(RankRepository rankRepository, RankCriteriaRepository rankCriteriaRepository, RankMapper rankMapper) {
        this.rankRepository = rankRepository;
        this.rankCriteriaRepository = rankCriteriaRepository;
        this.rankMapper = rankMapper;
    }

    private Rank saveRank(RankDto rankDto) {
        Rank newRank = rankMapper.dtoToEntity(rankDto);
        return rankRepository.save(newRank);
    }

    public List<RankDto> getAllRanksAsDtos() {
        List<Rank> ranks = new ArrayList<>(rankRepository.findAll());
        return rankMapper.entitiesToDtos(ranks);
    }

    public Page<RankDto> getAllRanksByFilterAsDtos(RankSearchFilter rankSearchFilter) throws EntityNotFoundException {
        if (rankSearchFilter == null) {
            rankSearchFilter = new RankSearchFilter();
        }

        Page<Rank> ranks = rankCriteriaRepository.findAllByFilter(rankSearchFilter);
        if (ranks.isEmpty()) {
            throw new EntityNotFoundException(Rank.class, Map.of("pageCriteria", rankSearchFilter.getPageCriteria().toString(), "rankCriteria", rankSearchFilter.getRankCriteria().toString()));
        }
        return rankMapper.entitiesToDtos(ranks);
    }

    public RankDto getRankByIdAsDto(short id) throws EntityNotFoundException {
        Optional<Rank> rank = rankRepository.findById(id);
        if (rank.isEmpty()) {
            throw new EntityNotFoundException(Rank.class, Map.of("id", String.valueOf(id)));
        }
        return rankMapper.entityToDto(rank.get());
    }

    public Rank getRankByName(String name) throws EntityNotFoundException {
        Optional<Rank> rank = rankRepository.findByName(name);
        if (rank.isEmpty()) {
            throw new EntityNotFoundException(Rank.class, Map.of("name", name));
        }
        return rank.get();
    }

    public RankDto createRank(RankDto rankDto) {
        return rankMapper.entityToDto(saveRank(rankDto));
    }

    public RankDto updateRankById(short id, RankDto rankDto) throws EntityNotFoundException {
        Optional<Rank> rank = rankRepository.findById(id);
        if (rank.isEmpty()) {
            throw new EntityNotFoundException(Rank.class, Map.of("id", String.valueOf(id)));
        }
        rankDto.setId(id);
        return rankMapper.entityToDto(saveRank(rankDto));
    }

    public RankDto deleteRankById(short id) throws EntityNotFoundException {
        Optional<Rank> rank = rankRepository.findById(id);
        if (rank.isEmpty()) {
            throw new EntityNotFoundException(Rank.class, Map.of("id", String.valueOf(id)));
        }
        rankRepository.deleteById(id);
        return rankMapper.entityToDto(rank.get());
    }
}
