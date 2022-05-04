package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import ru.nsu.fit.militarysystem.dto.SubjectDto;
import ru.nsu.fit.militarysystem.store.entity.Subject;

@Mapper(componentModel = "spring")
public interface SubjectMapper extends BaseMapper<Subject, SubjectDto> {
}
