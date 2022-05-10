package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nsu.fit.militarysystem.dto.MilitaryDistrictDto;
import ru.nsu.fit.militarysystem.service.DislocationService;
import ru.nsu.fit.militarysystem.service.MilitaryManService;
import ru.nsu.fit.militarysystem.store.entity.MilitaryDistrict;

@Mapper(componentModel = "spring")
public abstract class MilitaryDistrictMapper implements BaseMapper<MilitaryDistrict, MilitaryDistrictDto> {
    @Autowired
    protected MilitaryManService militaryManService;

    @Autowired
    protected DislocationService dislocationService;

    @Override
    @Mapping(target = "commander",
             expression = "java(militaryManService.getMilitaryManByIdentificationNumber(dto.getCommander().getIdentificationNumber()))")
    @Mapping(target = "headquartersDislocation",
             expression = "java(dislocationService.getDislocationByOkato(dto.getHeadquartersDislocation().getOkato()))")
    public abstract MilitaryDistrict dtoToEntity(MilitaryDistrictDto dto);
}
