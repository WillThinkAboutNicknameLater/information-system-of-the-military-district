package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nsu.fit.militarysystem.dto.DislocationDto;
import ru.nsu.fit.militarysystem.service.DislocationTypeService;
import ru.nsu.fit.militarysystem.service.SubjectService;
import ru.nsu.fit.militarysystem.store.entity.Dislocation;

@Mapper(componentModel = "spring")
public abstract class DislocationMapper implements BaseMapper<Dislocation, DislocationDto> {
    @Autowired
    protected DislocationTypeService dislocationTypeService;

    @Autowired
    protected SubjectService subjectService;

    @Override
    @Mapping(source = "dislocationType.name", target = "dislocationTypeName")
    @Mapping(source = "subject.name", target = "subjectName")
    public abstract DislocationDto entityToDto(Dislocation entity);

    @Override
    @Mapping(target = "dislocationType", expression = "java(dislocationTypeService.getDislocationTypeByName(dto.getDislocationTypeName()))")
    @Mapping(target = "subject", expression = "java(subjectService.getSubjectByName(dto.getSubjectName()))")
    public abstract Dislocation dtoToEntity(DislocationDto dto);
}
