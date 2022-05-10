package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.SubjectDto;
import ru.nsu.fit.militarysystem.filter.SubjectSearchFilter;
import ru.nsu.fit.militarysystem.mapper.SubjectMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityAlreadyExistsException;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.store.entity.Subject;
import ru.nsu.fit.militarysystem.store.repository.SubjectRepository;

import java.util.*;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    private final SubjectMapper subjectMapper;

    public SubjectService(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    private Subject saveSubject(SubjectDto subjectDto) {
        Subject newSubject = subjectMapper.dtoToEntity(subjectDto);
        return subjectRepository.save(newSubject);
    }

    public List<SubjectDto> getAllSubjectsAsDtos() {
        List<Subject> subjects = new ArrayList<>(subjectRepository.findAll());
        return subjectMapper.entitiesToDtos(subjects);
    }

    public Page<SubjectDto> getAllSubjectsByFilterAsDtos(SubjectSearchFilter subjectSearchFilter) throws EntityNotFoundException {
        Pageable pageable = PageRequest.of(subjectSearchFilter.getPageNumber(), subjectSearchFilter.getPageSize(),
                                           subjectSearchFilter.getSortDirection(),
                                           subjectSearchFilter.getSortBy().toArray(new String[]{}));
        String searchName = subjectSearchFilter.getSearchName();
        Page<Subject> subjects = subjectRepository.findAllByNameContainingIgnoreCase(searchName, pageable);

        if (subjects.isEmpty()) {
            throw new EntityNotFoundException(Subject[].class, Map.of("subjectSearchFilter", subjectSearchFilter.toString()));
        }

        return subjectMapper.entitiesToDtos(subjects);
    }

    public SubjectDto getSubjectByIdAsDto(short id) throws EntityNotFoundException {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isEmpty()) {
            throw new EntityNotFoundException(Subject.class, Map.of("id", String.valueOf(id)));
        }
        return subjectMapper.entityToDto(subject.get());
    }

    public Subject getSubjectByName(String name) throws EntityNotFoundException {
        Optional<Subject> subject = subjectRepository.findByName(name);
        if (subject.isEmpty()) {
            throw new EntityNotFoundException(Subject.class, Map.of("name", name));
        }
        return subject.get();
    }

    public SubjectDto createSubject(SubjectDto subjectDto) throws EntityAlreadyExistsException {
        Short id = subjectDto.getId();
        if (Objects.isNull(id)) {
            return subjectMapper.entityToDto(saveSubject(subjectDto));
        }
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            throw new EntityAlreadyExistsException(Subject.class, Map.of("id", String.valueOf(id)));
        }
        return subjectMapper.entityToDto(saveSubject(subjectDto));
    }

    public SubjectDto updateSubjectById(short id, SubjectDto subjectDto) throws EntityNotFoundException {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isEmpty()) {
            throw new EntityNotFoundException(Subject.class, Map.of("id", String.valueOf(id)));
        }
        subjectDto.setId(id);
        return subjectMapper.entityToDto(saveSubject(subjectDto));
    }

    public SubjectDto deleteSubjectById(short id) throws EntityNotFoundException {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isEmpty()) {
            throw new EntityNotFoundException(Subject.class, Map.of("id", String.valueOf(id)));
        }
        subjectRepository.deleteById(id);
        return subjectMapper.entityToDto(subject.get());
    }
}
