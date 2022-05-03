package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.MilitaryManDto;
import ru.nsu.fit.militarysystem.service.RankService;
import ru.nsu.fit.militarysystem.store.entity.MilitaryMan;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MilitaryManMapper {
    @Autowired
    protected RankService rankService;

    @Mapping(source = "rank.name", target = "rank")
    public abstract MilitaryManDto entityToDto(MilitaryMan militaryMan);

    @Mapping(target = "rank", expression = "java(rankService.getRankByName(militaryManDto.getRank()))")
    public abstract MilitaryMan dtoToEntity(MilitaryManDto militaryManDto);

    public abstract List<MilitaryManDto> entitiesToDtos(List<MilitaryMan> militaryMen);

    public abstract List<MilitaryMan> dtosToEntities(List<MilitaryManDto> militaryManDtos);

    public Page<MilitaryManDto> entitiesToDtos(Page<MilitaryMan> militaryMen) {
        return militaryMen.map(this::entityToDto);
    }

    public Page<MilitaryMan> dtosToEntities(Page<MilitaryManDto> militaryManDtos) {
        return militaryManDtos.map(this::dtoToEntity);
    }
}
