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
    protected DislocationService dislocationService;

    @Autowired
    protected MilitaryManService militaryManService;

    @Override
    @Mapping(source = "headquartersDislocation.id", target = "headquartersDislocationId")
    @Mapping(source = "headquartersDislocation.name", target = "headquartersDislocationName")
    public abstract MilitaryDistrictDto entityToDto(MilitaryDistrict entity);

    @Override
    @Mapping(target = "headquartersDislocation", expression = "java(dislocationService.getDislocationById(dto.getHeadquartersDislocationId()))")
    @Mapping(target = "commander", expression = "java(militaryManService.getMilitaryManByIdentificationNumber(dto.getCommander().getIdentificationNumber()))")
    public abstract MilitaryDistrict dtoToEntity(MilitaryDistrictDto dto);
}
