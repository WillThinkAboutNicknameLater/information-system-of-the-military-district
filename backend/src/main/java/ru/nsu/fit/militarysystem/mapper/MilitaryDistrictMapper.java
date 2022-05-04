package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.MilitaryDistrictDto;
import ru.nsu.fit.militarysystem.service.DislocationService;
import ru.nsu.fit.militarysystem.service.MilitaryManService;
import ru.nsu.fit.militarysystem.store.entity.MilitaryDistrict;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class MilitaryDistrictMapper {
    @Autowired
    protected DislocationService dislocationService;

    @Autowired
    protected MilitaryManService militaryManService;

    @Mapping(source = "headquartersDislocation.id", target = "headquartersDislocationId")
    @Mapping(source = "headquartersDislocation.name", target = "headquartersDislocationName")
    public abstract MilitaryDistrictDto entityToDto(MilitaryDistrict militaryDistrict);

    @Mapping(target = "headquartersDislocation", expression = "java(dislocationService.getDislocationById(militaryDistrictDto.getHeadquartersDislocationId()))")
    @Mapping(target = "commander", expression = "java(militaryManService.getMilitaryManByIdentificationNumber(militaryDistrictDto.getCommander().getIdentificationNumber()))")
    public abstract MilitaryDistrict dtoToEntity(MilitaryDistrictDto militaryDistrictDto);

    public abstract List<MilitaryDistrictDto> entitiesToDtos(List<MilitaryDistrict> militaryDistricts);

    public abstract List<MilitaryDistrict> dtosToEntities(List<MilitaryDistrictDto> militaryDistrictDtos);

    public Page<MilitaryDistrictDto> entitiesToDtos(Page<MilitaryDistrict> militaryDistricts) {
        return militaryDistricts.map(this::entityToDto);
    }

    public Page<MilitaryDistrict> dtosToEntities(Page<MilitaryDistrictDto> militaryDistrictDtos) {
        return militaryDistrictDtos.map(this::dtoToEntity);
    }
}
