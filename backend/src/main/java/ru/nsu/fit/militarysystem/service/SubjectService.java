package ru.nsu.fit.militarysystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.nsu.fit.militarysystem.dto.SubjectDto;
import ru.nsu.fit.militarysystem.mapper.SubjectMapper;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.criteria.PageCriteria;
import ru.nsu.fit.militarysystem.filter.SubjectSearchFilter;
import ru.nsu.fit.militarysystem.store.entity.Subject;
import ru.nsu.fit.militarysystem.store.repository.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public List<SubjectDto> getAllStaffCategoriesAsDtos() {
        List<Subject> subjects = new ArrayList<>(subjectRepository.findAll());
        return subjectMapper.entitiesToDtos(subjects);
    }

    public Page<SubjectDto> getAllStaffCategoriesByFilterAsDtos(SubjectSearchFilter subjectSearchFilter) throws EntityNotFoundException {
        if (subjectSearchFilter == null) {
            subjectSearchFilter = new SubjectSearchFilter();
        }

        PageCriteria pageCriteria = subjectSearchFilter.getPageCriteria();
        Pageable pageable = PageRequest.of(pageCriteria.getPageNumber(), pageCriteria.getPageSize(), pageCriteria.getSortDirection(), pageCriteria.getSortBy().toArray(new String[]{}));
        Page<Subject> subjects = subjectRepository.findAll(pageable);
        if (subjects.isEmpty()) {
            throw new EntityNotFoundException(Subject.class, Map.of("pageCriteria", pageCriteria.toString()));
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

    public SubjectDto createSubject(SubjectDto subjectDto) {
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
