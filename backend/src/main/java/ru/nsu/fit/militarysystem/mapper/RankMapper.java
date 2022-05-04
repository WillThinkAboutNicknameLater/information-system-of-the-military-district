package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.RankDto;
import ru.nsu.fit.militarysystem.service.RankCategoryService;
import ru.nsu.fit.militarysystem.service.StaffCategoryService;
import ru.nsu.fit.militarysystem.store.entity.Rank;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RankMapper {
    @Autowired
    protected StaffCategoryService staffCategoryService;

    @Autowired
    protected RankCategoryService rankCategoryService;

    @Mapping(source = "staffCategory.name", target = "staffCategoryName")
    @Mapping(source = "rankCategory.name", target = "rankCategoryName")
    public abstract RankDto entityToDto(Rank rank);

    @Mapping(target = "staffCategory", expression = "java(staffCategoryService.getStaffCategoryByName(rankDto.getStaffCategoryName()))")
    @Mapping(target = "rankCategory", expression = "java(rankCategoryService.getRankCategoryByName(rankDto.getRankCategoryName()))")
    public abstract Rank dtoToEntity(RankDto rankDto);

    public abstract List<RankDto> entitiesToDtos(List<Rank> ranks);

    public abstract List<Rank> dtosToEntities(List<RankDto> rankDtos);

    public Page<RankDto> entitiesToDtos(Page<Rank> ranks) {
        return ranks.map(this::entityToDto);
    }

    public Page<Rank> dtosToEntities(Page<RankDto> rankDtos) {
        return rankDtos.map(this::dtoToEntity);
    }
}
