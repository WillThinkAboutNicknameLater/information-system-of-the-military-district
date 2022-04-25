package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.SubjectDto;
import ru.nsu.fit.militarysystem.service.SubjectService;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.SubjectSearchFilter;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class SubjectController {
    private final SubjectService subjectService;

    private static final String GET_SUBJECTS = "/subjects";

    private static final String GET_SUBJECTS_WITH_SEARCH_FILTER = "/subjects/search";

    private static final String GET_SUBJECT = "/subjects/{id}";

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(GET_SUBJECTS)
    public ResponseEntity<List<SubjectDto>> getAllSubjects() {
        List<SubjectDto> subjects = subjectService.getAllSubjects();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping(GET_SUBJECTS_WITH_SEARCH_FILTER)
    public ResponseEntity<Page<SubjectDto>> getAllSubjectsWithFilters(@RequestBody(required = false) SubjectSearchFilter subjectSearchFilter) throws EntityNotFoundException {
        Page<SubjectDto> subjects = subjectService.getAllSubjectsWithFilters(subjectSearchFilter);
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping(GET_SUBJECT)
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable("id") short id) throws EntityNotFoundException {
        SubjectDto subject = subjectService.getSubjectById(id);
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

}
