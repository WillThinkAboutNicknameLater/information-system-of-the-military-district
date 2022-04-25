package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.RankDto;
import ru.nsu.fit.militarysystem.mapper.RankMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.RankSearchFilter;
import ru.nsu.fit.militarysystem.store.entity.Rank;
import ru.nsu.fit.militarysystem.store.entity.Subject;
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

    public RankService(RankRepository rankRepository, RankCriteriaRepository rankCriteriaRepository) {
        this.rankRepository = rankRepository;
        this.rankCriteriaRepository = rankCriteriaRepository;
    }

    public List<RankDto> getAllRanks() {
        List<Rank> ranks = new ArrayList<>(rankRepository.findAll());
        return RankMapper.INSTANCE.ranksToRankDtos(ranks);
    }

    public Page<RankDto> getAllRanksWithFilters(RankSearchFilter rankSearchFilter) throws EntityNotFoundException {
        if (rankSearchFilter == null) {
            rankSearchFilter = new RankSearchFilter();
        }
        Page<Rank> ranks = rankCriteriaRepository.findAllWithFilters(rankSearchFilter);
        if (ranks.isEmpty()) {
            throw new EntityNotFoundException(Subject.class, Map.of("pageCriteria", rankSearchFilter.getPageCriteria().toString(), "rankCriteria", rankSearchFilter.getRankCriteria().toString()));
        }
        return RankMapper.INSTANCE.ranksToRankDtos(ranks);
    }

    public RankDto getRankById(short id) throws EntityNotFoundException {
        Optional<Rank> rank = rankRepository.findById(id);
        if (rank.isEmpty()) {
            throw new EntityNotFoundException(Rank.class, Map.of("id", String.valueOf(id)));
        }
        return RankMapper.INSTANCE.rankToRankDto(rank.get());
    }

    public RankDto createRank(RankDto rank) throws EntityAlreadyExistException {
        Optional<Rank> rankWithSameID = rankRepository.findById(rank.getId());
        if (rankWithSameID.isPresent()) {
            throw new EntityAlreadyExistException(Rank.class, Map.of("id", String.valueOf(rank.getId())));
        }
        return RankMapper.INSTANCE.rankToRankDto(rankRepository.save(RankMapper.INSTANCE.rankDtoToRank(rank)));
    }

    public RankDto deleteRank(short id) throws EntityNotFoundException {
        Optional<Rank> rank = rankRepository.findById(id);
        if (rank.isEmpty()) {
            throw new EntityNotFoundException(Rank.class, Map.of("id", String.valueOf(id)));
        }
        rankRepository.deleteById(id);
        return RankMapper.INSTANCE.rankToRankDto(rank.get());
    }
}
