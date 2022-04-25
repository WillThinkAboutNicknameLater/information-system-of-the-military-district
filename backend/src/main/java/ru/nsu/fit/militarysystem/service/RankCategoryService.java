package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.RankCategoryDto;
import ru.nsu.fit.militarysystem.mapper.RankCategoryMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;
import ru.nsu.fit.militarysystem.filter.RankCategorySearchFilter;
import ru.nsu.fit.militarysystem.store.entity.RankCategory;
import ru.nsu.fit.militarysystem.store.entity.StaffCategory;
import ru.nsu.fit.militarysystem.store.repository.RankCategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RankCategoryService {
    private final RankCategoryRepository rankCategoryRepository;

    public RankCategoryService(RankCategoryRepository rankCategoryRepository) {
        this.rankCategoryRepository = rankCategoryRepository;
    }

    public List<RankCategoryDto> getAllRankCategories() {
        List<RankCategory> rankCategories = new ArrayList<>(rankCategoryRepository.findAll());
        return RankCategoryMapper.INSTANCE.rankCategoriesToRankCategoryDtos(rankCategories);
    }

    public Page<RankCategoryDto> getAllRankCategoriesWithFilters(RankCategorySearchFilter rankCategorySearchFilter) throws EntityNotFoundException {
        if (rankCategorySearchFilter == null) {
            rankCategorySearchFilter = new RankCategorySearchFilter();
        }
        PageCriteria pageCriteria = rankCategorySearchFilter.getPageCriteria();
        Pageable pageable = PageRequest.of(pageCriteria.getPageNumber(), pageCriteria.getPageSize(), pageCriteria.getSortDirection(), pageCriteria.getSortBy().toArray(new String[]{}));
        Page<RankCategory> rankCategories = rankCategoryRepository.findAll(pageable);
        if (rankCategories.isEmpty()) {
            throw new EntityNotFoundException(RankCategory.class, Map.of("pageCriteria", pageCriteria.toString()));
        }
        return RankCategoryMapper.INSTANCE.rankCategoriesToRankCategoryDtos(rankCategories);
    }

    public RankCategoryDto getRankCategoryById(short id) throws EntityNotFoundException {
        Optional<RankCategory> rankCategory = rankCategoryRepository.findById(id);
        if (rankCategory.isEmpty()) {
            throw new EntityNotFoundException(RankCategory.class, Map.of("id", String.valueOf(id)));
        }
        return RankCategoryMapper.INSTANCE.rankCategoryToRankCategoryDto(rankCategory.get());
    }

    public RankCategoryDto getRankCategoryByName(String name) throws EntityNotFoundException {
        Optional<RankCategory> rankCategory = rankCategoryRepository.findByName(name);
        if (rankCategory.isEmpty()) {
            throw new EntityNotFoundException(StaffCategory.class, Map.of("name", name));
        }
        return RankCategoryMapper.INSTANCE.rankCategoryToRankCategoryDto(rankCategory.get());
    }
}
