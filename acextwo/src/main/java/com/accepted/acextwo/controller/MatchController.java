package com.accepted.acextwo.controller;

import com.accepted.acextwo.dto.MatchDTO;
import com.accepted.acextwo.dto.MatchWithOddsDTO;
import com.accepted.acextwo.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matches")
@Tag(
        name = "Match Resource",
        description = """
    This API handles the management of matches.
    
    **Constraints**:
    - **Sport**: Allowed values are `1` (Football) and `2` (Basketball).
    - **Match Date**: Must not be in the past.
    - **Team A and Team B**: Cannot be the same.
    - **Unique Match**: A match is uniquely identified by a combination of `Sport`, `Match Date`, and either `Team A` or `Team B`.
    - **Description**: Must not exceed 255 characters.
    """
)
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @PostMapping
    @Operation(
            summary = "Create a new match",
            description = "Create a new match with details: description, matchDate, matchTime, teamA, teamB, sport",
            tags = {"Match Resource"}
    )
    public ResponseEntity<MatchDTO> createMatch(
            @Valid @RequestBody MatchDTO matchDto) {
        return new ResponseEntity<>(matchService.createMatch(matchDto), HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    @Operation(
            summary = "Create multiple matches",
            description = "Create multiple matches in bulk by providing a list of match details.",
            tags = {"Bulk Create"}
    )
    public ResponseEntity<List<MatchDTO>> createMatches(
            @Valid @RequestBody
            List<MatchDTO> matchDTOS) {
        List<MatchDTO> createdMatches = matchService.createMatches(matchDTOS);
        return new ResponseEntity<>(createdMatches, HttpStatus.CREATED);
    }

    @PostMapping("/with-odds")
    @Operation(
            summary = "Create a match with odds",
            description = "Create a match along with its associated odds in a single operation.",
            tags = {"Match Resource", "Create"}
    )
    public ResponseEntity<MatchWithOddsDTO> createMatchWithOdds(
            @Valid @RequestBody MatchWithOddsDTO matchWithOddsDTO) {
        MatchWithOddsDTO createdMatch = matchService.createMatchWithOdds(matchWithOddsDTO);
        return new ResponseEntity<>(createdMatch, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Retrieve all matches",
            description = "Fetch a list of all matches, including optional filtering capabilities.",
            tags = {"Match Resource"}
    )
    public ResponseEntity<List<MatchWithOddsDTO>> getAllMatches() {
        return new ResponseEntity<>(matchService.getAllMatches(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get match by ID",
            description = "Retrieve details of a specific match by its unique identifier, including associated odds.",
            tags = {"Match Resource"}
    )
    public ResponseEntity<MatchWithOddsDTO> getMatchById(@PathVariable("id") long matchId) {
        return new ResponseEntity<>(matchService.getMatchById(matchId), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing match",
            description = "Update details of an existing match by its unique identifier, including description, teams, and sport.",
            tags = {"Match Resource"}
    )
    public ResponseEntity<MatchDTO> updateMatch(
            @Valid @RequestBody MatchDTO matchDto,
            @PathVariable("id") long matchId) {
        return new ResponseEntity<>(matchService.updateMatch(matchDto, matchId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a match by ID",
            description = "Delete a match and its associated odds using its unique identifier.",
            tags = {"Match Resource"}
    )
    public ResponseEntity<String> deleteMatchById(@PathVariable("id") long matchId) {
        matchService.deleteMatchById(matchId);
        return ResponseEntity.ok("Match Entity with id="+ matchId +" deleted successfully!");
    }

}
