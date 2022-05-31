package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.RankCategoryDto;
import ru.nsu.fit.militarysystem.filter.RankCategorySearchFilter;
import ru.nsu.fit.militarysystem.mapper.RankCategoryMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.RankCategory;
import ru.nsu.fit.militarysystem.store.repository.RankCategoryRepository;

import java.util.*;

@Service
public class RankCategoryService {
    private final RankCategoryRepository rankCategoryRepository;

    private final RankCategoryMapper rankCategoryMapper;

    public RankCategoryService(RankCategoryRepository rankCategoryRepository, RankCategoryMapper rankCategoryMapper) {
        this.rankCategoryRepository = rankCategoryRepository;
        this.rankCategoryMapper = rankCategoryMapper;
    }

    private RankCategory saveRankCategory(RankCategoryDto rankCategoryDto) {
        RankCategory newRankCategory = rankCategoryMapper.dtoToEntity(rankCategoryDto);
        return rankCategoryRepository.save(newRankCategory);
    }

    public List<RankCategoryDto> getAllRankCategoriesAsDtos() {
        List<RankCategory> rankCategories = new ArrayList<>(rankCategoryRepository.findAll());
        return rankCategoryMapper.entitiesToDtos(rankCategories);
    }

    public Page<RankCategoryDto> getAllRankCategoriesByFilterAsDtos(RankCategorySearchFilter rankCategorySearchFilter)
            throws EntityNotFoundException {
        Pageable pageable = PageRequest.of(rankCategorySearchFilter.getPageNumber(), rankCategorySearchFilter.getPageSize(),
                                           rankCategorySearchFilter.getSortDirection(),
                                           rankCategorySearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = rankCategorySearchFilter.getSearchName();
        Page<RankCategory> rankCategories = rankCategoryRepository.findAllByNameContainingIgnoreCase(searchName, pageable);

        return rankCategoryMapper.entitiesToDtos(rankCategories);
    }

    public RankCategoryDto getRankCategoryByIdAsDto(short id) throws EntityNotFoundException {
        Optional<RankCategory> rankCategory = rankCategoryRepository.findById(id);
        if (rankCategory.isEmpty()) {
            throw new EntityNotFoundException(RankCategory.class, Map.of("id", String.valueOf(id)));
        }
        return rankCategoryMapper.entityToDto(rankCategory.get());
    }

    public RankCategory getRankCategoryByName(String name) throws EntityNotFoundException {
        Optional<RankCategory> rankCategory = rankCategoryRepository.findByName(name);
        if (rankCategory.isEmpty()) {
            throw new EntityNotFoundException(RankCategory.class, Map.of("name", name));
        }
        return rankCategory.get();
    }

    public RankCategoryDto createRankCategory(RankCategoryDto rankCategoryDto) throws EntityAlreadyExistsException {
        Short id = rankCategoryDto.getId();
        if (Objects.isNull(id)) {
            return rankCategoryMapper.entityToDto(saveRankCategory(rankCategoryDto));
        }
        Optional<RankCategory> rankCategory = rankCategoryRepository.findById(id);
        if (rankCategory.isPresent()) {
            throw new EntityAlreadyExistsException(RankCategory.class, Map.of("id", String.valueOf(id)));
        }
        return rankCategoryMapper.entityToDto(saveRankCategory(rankCategoryDto));
    }

    public RankCategoryDto updateRankCategoryById(short id, RankCategoryDto rankCategoryDto) throws EntityNotFoundException {
        Optional<RankCategory> rankCategory = rankCategoryRepository.findById(id);
        if (rankCategory.isEmpty()) {
            throw new EntityNotFoundException(RankCategory.class, Map.of("id", String.valueOf(id)));
        }
        rankCategoryDto.setId(id);
        return rankCategoryMapper.entityToDto(saveRankCategory(rankCategoryDto));
    }

    public RankCategoryDto deleteRankCategoryById(short id) throws EntityNotFoundException {
        Optional<RankCategory> rankCategory = rankCategoryRepository.findById(id);
        if (rankCategory.isEmpty()) {
            throw new EntityNotFoundException(RankCategory.class, Map.of("id", String.valueOf(id)));
        }
        rankCategoryRepository.deleteById(id);
        return rankCategoryMapper.entityToDto(rankCategory.get());
    }
}
