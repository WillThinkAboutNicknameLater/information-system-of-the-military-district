package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.SubjectDto;
import ru.nsu.fit.militarysystem.store.entity.Subject;

import java.util.List;

@Mapper
public interface SubjectMapper {
    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    SubjectDto subjectToSubjectDto(Subject subject);

    Subject subjectDtoToSubject(SubjectDto subjectDto);

    List<SubjectDto> subjectsToSubjectDtos(List<Subject> subjects);

    List<Subject> subjectDtosToSubjects(List<SubjectDto> subjectDtos);

    default Page<SubjectDto> subjectsToSubjectDtos(Page<Subject> subjects) {
        return subjects.map(INSTANCE::subjectToSubjectDto);
    }

    default Page<Subject> subjectDtosToSubjects(Page<SubjectDto> subjectDtos) {
        return subjectDtos.map(INSTANCE::subjectDtoToSubject);
    }
}
