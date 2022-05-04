package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.DislocationDto;
import ru.nsu.fit.militarysystem.service.DislocationTypeService;
import ru.nsu.fit.militarysystem.service.SubjectService;
import ru.nsu.fit.militarysystem.store.entity.Dislocation;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DislocationMapper {
    @Autowired
    protected DislocationTypeService dislocationTypeService;

    @Autowired
    protected SubjectService subjectService;

    @Mapping(source = "dislocationType.name", target = "dislocationTypeName")
    @Mapping(source = "subject.name", target = "subjectName")
    public abstract DislocationDto entityToDto(Dislocation dislocation);

    @Mapping(target = "dislocationType", expression = "java(dislocationTypeService.getDislocationTypeByName(dislocationDto.getDislocationTypeName()))")
    @Mapping(target = "subject", expression = "java(subjectService.getSubjectByName(dislocationDto.getSubjectName()))")
    public abstract Dislocation dtoToEntity(DislocationDto dislocationDto);

    public abstract List<DislocationDto> entitiesToDtos(List<Dislocation> dislocations);

    public abstract List<Dislocation> dtosToEntities(List<DislocationDto> dislocationDtos);

    public Page<DislocationDto> entitiesToDtos(Page<Dislocation> dislocations) {
        return dislocations.map(this::entityToDto);
    }

    public Page<Dislocation> dtosToEntities(Page<DislocationDto> dislocationDtos) {
        return dislocationDtos.map(this::dtoToEntity);
    }
}
