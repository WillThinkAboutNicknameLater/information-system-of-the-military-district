package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import ru.nsu.fit.militarysystem.dto.MilitaryFormationDto;
import ru.nsu.fit.militarysystem.service.DislocationService;
import ru.nsu.fit.militarysystem.service.MilitaryFormationTypeService;
import ru.nsu.fit.militarysystem.service.MilitaryManService;
import ru.nsu.fit.militarysystem.store.entity.MilitaryFormation;

@Mapper(componentModel = "spring")
public abstract class MilitaryFormationMapper implements BaseMapper<MilitaryFormation, MilitaryFormationDto> {
    @Autowired
    protected MilitaryFormationTypeService militaryFormationTypeService;

    @Lazy
    @Autowired
    protected MilitaryManService militaryManService;

    @Autowired
    protected DislocationService dislocationService;

    @Override
    @Mapping(source = "militaryFormationType.name", target = "militaryFormationTypeName")
    public abstract MilitaryFormationDto entityToDto(MilitaryFormation entity);

    @Override
    @Mapping(target = "militaryFormationType",
             expression = "java(militaryFormationTypeService.getMilitaryFormationTypeByName(dto.getMilitaryFormationTypeName()))")
    @Mapping(target = "commander",
             expression = "java(militaryManService.getMilitaryManByIdentificationNumber(dto.getCommander().getIdentificationNumber()))")
    @Mapping(target = "dislocation",
             expression = "java(dislocationService.getDislocationByOkato(dto.getDislocation().getOkato()))")
    public abstract MilitaryFormation dtoToEntity(MilitaryFormationDto dto);
}
