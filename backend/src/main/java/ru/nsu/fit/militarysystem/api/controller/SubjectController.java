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

    private static final String POST_SUBJECT = "/subjects";

    private static final String PUT_SUBJECT = "/subjects/{id}";

    private static final String DELETE_SUBJECT = "/subjects/{id}";

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(GET_SUBJECTS)
    public ResponseEntity<List<SubjectDto>> getAllStaffCategories() {
        List<SubjectDto> subjectDtos = subjectService.getAllStaffCategoriesAsDtos();
        return new ResponseEntity<>(subjectDtos, HttpStatus.OK);
    }

    @GetMapping(GET_SUBJECTS_WITH_SEARCH_FILTER)
    public ResponseEntity<Page<SubjectDto>> getAllStaffCategoriesByFilter(@RequestBody(required = false) SubjectSearchFilter subjectSearchFilter) throws EntityNotFoundException {
        Page<SubjectDto> subjectDtos = subjectService.getAllStaffCategoriesByFilterAsDtos(subjectSearchFilter);
        return new ResponseEntity<>(subjectDtos, HttpStatus.OK);
    }

    @GetMapping(GET_SUBJECT)
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable("id") short id) throws EntityNotFoundException {
        SubjectDto subjectDto = subjectService.getSubjectByIdAsDto(id);
        return new ResponseEntity<>(subjectDto, HttpStatus.OK);
    }

    @PostMapping(POST_SUBJECT)
    public ResponseEntity<SubjectDto> createSubject(@RequestBody SubjectDto subjectDto) {
        return new ResponseEntity<>(subjectService.createSubject(subjectDto), HttpStatus.OK);
    }

    @PutMapping(PUT_SUBJECT)
    public ResponseEntity<SubjectDto> updateSubjectById(@PathVariable("id") short id, @RequestBody SubjectDto subjectDto) throws EntityNotFoundException {
        return new ResponseEntity<>(subjectService.updateSubjectById(id, subjectDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_SUBJECT)
    public ResponseEntity<SubjectDto> deleteSubjectById(@PathVariable("id") short id) throws EntityNotFoundException {
        SubjectDto subjectDto = subjectService.deleteSubjectById(id);
        return new ResponseEntity<>(subjectDto, HttpStatus.OK);
    }

}
