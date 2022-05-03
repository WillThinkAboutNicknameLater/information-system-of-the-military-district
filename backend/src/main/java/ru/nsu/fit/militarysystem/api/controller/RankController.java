package ru.nsu.fit.militarysystem.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.militarysystem.dto.RankDto;
import ru.nsu.fit.militarysystem.service.RankService;
import ru.nsu.fit.militarysystem.service.exception.EntityNotFoundException;
import ru.nsu.fit.militarysystem.filter.RankSearchFilter;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class RankController {
    private final RankService rankService;

    private static final String GET_RANKS = "/ranks";

    private static final String GET_RANKS_WITH_SEARCH_FILTER = "/ranks/search";

    private static final String GET_RANK = "/ranks/{id}";

    private static final String POST_RANK = "/ranks";

    private static final String PUT_RANK = "/ranks/{id}";

    private static final String DELETE_RANK = "/ranks/{id}";

    public RankController(RankService rankService) {
        this.rankService = rankService;
    }

    @GetMapping(GET_RANKS)
    public ResponseEntity<List<RankDto>> getAllRanks() {
        List<RankDto> rankDtos = rankService.getAllRanksAsDtos();
        return new ResponseEntity<>(rankDtos, HttpStatus.OK);
    }

    @GetMapping(GET_RANKS_WITH_SEARCH_FILTER)
    public ResponseEntity<Page<RankDto>> getAllRanksByFilter(@RequestBody(required = false) RankSearchFilter rankSearchFilter) throws EntityNotFoundException {
        Page<RankDto> rankDtos = rankService.getAllRanksByFilterAsDtos(rankSearchFilter);
        return new ResponseEntity<>(rankDtos, HttpStatus.OK);
    }

    @GetMapping(GET_RANK)
    public ResponseEntity<RankDto> getRankById(@PathVariable("id") short id) throws EntityNotFoundException {
        RankDto rankDto = rankService.getRankByIdAsDto(id);
        return new ResponseEntity<>(rankDto, HttpStatus.OK);
    }

    @PostMapping(POST_RANK)
    public ResponseEntity<RankDto> createRank(@RequestBody RankDto rankDto) {
        return new ResponseEntity<>(rankService.createRank(rankDto), HttpStatus.OK);
    }

    @PutMapping(PUT_RANK)
    public ResponseEntity<RankDto> updateRankById(@PathVariable("id") short id, @RequestBody RankDto rankDto) throws EntityNotFoundException {
        return new ResponseEntity<>(rankService.updateRankById(id, rankDto), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_RANK)
    public ResponseEntity<RankDto> deleteRankById(@PathVariable("id") short id) throws EntityNotFoundException {
        RankDto rankDto = rankService.deleteRankById(id);
        return new ResponseEntity<>(rankDto, HttpStatus.OK);
    }

}
