package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.RankCategoryDto;
import ru.nsu.fit.militarysystem.store.entity.RankCategory;

import java.util.List;

@Mapper
public interface RankCategoryMapper {
    RankCategoryMapper INSTANCE = Mappers.getMapper(RankCategoryMapper.class);

    RankCategoryDto rankCategoryToRankCategoryDto(RankCategory rankCategory);

    RankCategory rankCategoryDtoToRankCategory(RankCategoryDto rankCategoryDto);

    List<RankCategoryDto> rankCategoriesToRankCategoryDtos(List<RankCategory> rankCategories);

    List<RankCategory> rankCategoryDtosToRankCategories(List<RankCategoryDto> rankCategoryDtos);

    default Page<RankCategoryDto> rankCategoriesToRankCategoryDtos(Page<RankCategory> rankCategories) {
        return rankCategories.map(INSTANCE::rankCategoryToRankCategoryDto);
    }

    default Page<RankCategory> rankCategoryDtosToRankCategories(Page<RankCategoryDto> rankCategoryDtos) {
        return rankCategoryDtos.map(INSTANCE::rankCategoryDtoToRankCategory);
    }
}
