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

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<SubjectDto> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return SubjectMapper.INSTANCE.subjectsToSubjectDtos(subjects);
    }

    public Page<SubjectDto> getAllSubjectsWithFilters(SubjectSearchFilter subjectSearchFilter) throws EntityNotFoundException {
        if (subjectSearchFilter == null) {
            subjectSearchFilter = new SubjectSearchFilter();
        }
        PageCriteria pageCriteria = subjectSearchFilter.getPageCriteria();
        Pageable pageable = PageRequest.of(pageCriteria.getPageNumber(), pageCriteria.getPageSize(), pageCriteria.getSortDirection(), pageCriteria.getSortBy().toArray(new String[]{}));
        Page<Subject> subjects = subjectRepository.findAll(pageable);
        if (subjects.isEmpty()) {
            throw new EntityNotFoundException(Subject.class, Map.of("pageCriteria", pageCriteria.toString()));
        }
        return SubjectMapper.INSTANCE.subjectsToSubjectDtos(subjects);
    }

    public SubjectDto getSubjectById(short id) throws EntityNotFoundException {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isEmpty()) {
            throw new EntityNotFoundException(Subject.class, Map.of("id", String.valueOf(id)));
        }
        return SubjectMapper.INSTANCE.subjectToSubjectDto(subject.get());
    }
}
