package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nsu.fit.militarysystem.dto.RankDto;
import ru.nsu.fit.militarysystem.service.RankCategoryService;
import ru.nsu.fit.militarysystem.service.StaffCategoryService;
import ru.nsu.fit.militarysystem.store.entity.Rank;

@Mapper(componentModel = "spring")
public abstract class RankMapper implements BaseMapper<Rank, RankDto> {
    @Autowired
    protected RankCategoryService rankCategoryService;

    @Autowired
    protected StaffCategoryService staffCategoryService;

    @Override
    @Mapping(source = "rankCategory.name", target = "rankCategoryName")
    @Mapping(source = "staffCategory.name", target = "staffCategoryName")
    public abstract RankDto entityToDto(Rank entity);

    @Override
    @Mapping(target = "rankCategory", expression = "java(rankCategoryService.getRankCategoryByName(dto.getRankCategoryName()))")
    @Mapping(target = "staffCategory", expression = "java(staffCategoryService.getStaffCategoryByName(dto.getStaffCategoryName()))")
    public abstract Rank dtoToEntity(RankDto dto);
}
