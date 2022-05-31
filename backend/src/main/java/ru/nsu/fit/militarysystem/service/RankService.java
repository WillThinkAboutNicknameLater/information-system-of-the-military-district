package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.RankDto;
import ru.nsu.fit.militarysystem.filter.RankSearchFilter;
import ru.nsu.fit.militarysystem.mapper.RankMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.Rank;
import ru.nsu.fit.militarysystem.store.repository.RankRepository;
import ru.nsu.fit.militarysystem.util.IterableToPostgresqlArrayConverter;

import java.util.*;

@Service
public class RankService {
    private final RankRepository rankRepository;

    private final RankMapper rankMapper;

    public RankService(RankRepository rankRepository, RankMapper rankMapper) {
        this.rankRepository = rankRepository;
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
        Pageable pageable = PageRequest.of(rankSearchFilter.getPageNumber(), rankSearchFilter.getPageSize(),
                                           rankSearchFilter.getSortDirection(),
                                           rankSearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = rankSearchFilter.getSearchName();
        String rankCategoryIds = IterableToPostgresqlArrayConverter.convert(rankSearchFilter.getRankCategoryIds());
        String staffCategoryIds = IterableToPostgresqlArrayConverter.convert(rankSearchFilter.getStaffCategoryIds());
        Page<Rank> ranks = rankRepository.findAllByFilter(searchName, rankCategoryIds, staffCategoryIds, pageable);

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

    public RankDto createRank(RankDto rankDto) throws EntityAlreadyExistsException {
        Short id = rankDto.getId();
        if (Objects.isNull(id)) {
            return rankMapper.entityToDto(saveRank(rankDto));
        }
        Optional<Rank> rank = rankRepository.findById(id);
        if (rank.isPresent()) {
            throw new EntityAlreadyExistsException(Rank.class, Map.of("id", String.valueOf(id)));
        }
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
