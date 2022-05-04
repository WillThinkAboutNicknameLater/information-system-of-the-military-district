package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nsu.fit.militarysystem.dto.MilitaryManDto;
import ru.nsu.fit.militarysystem.service.RankService;
import ru.nsu.fit.militarysystem.store.entity.MilitaryMan;

@Mapper(componentModel = "spring")
public abstract class MilitaryManMapper implements BaseMapper<MilitaryMan, MilitaryManDto> {
    @Autowired
    protected RankService rankService;

    @Override
    @Mapping(source = "rank.name", target = "rankName")
    public abstract MilitaryManDto entityToDto(MilitaryMan entity);

    @Override
    @Mapping(target = "rank", expression = "java(rankService.getRankByName(dto.getRankName()))")
    public abstract MilitaryMan dtoToEntity(MilitaryManDto dto);
}
