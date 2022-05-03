package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.SubjectDto;
import ru.nsu.fit.militarysystem.store.entity.Subject;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SubjectMapper {
    public abstract SubjectDto entityToDto(Subject subject);

    public abstract Subject dtoToEntity(SubjectDto subjectDto);

    public abstract List<SubjectDto> entitiesToDtos(List<Subject> subjects);

    public abstract List<Subject> dtosToEntities(List<SubjectDto> subjectDtos);

    public Page<SubjectDto> entitiesToDtos(Page<Subject> subjects) {
        return subjects.map(this::entityToDto);
    }

    public Page<Subject> dtosToEntities(Page<SubjectDto> subjectDtos) {
        return subjectDtos.map(this::dtoToEntity);
    }
}
