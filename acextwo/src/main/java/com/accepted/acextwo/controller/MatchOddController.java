package com.accepted.acextwo.controller;

import com.accepted.acextwo.dto.MatchOddDTO;
import com.accepted.acextwo.dto.UpdateMatchOddDTO;
import com.accepted.acextwo.service.MatchOddService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/matches")
@Tag(
        name = "Match Odds Resource",
        description = """
    This API manages odds for matches.
    
    **Constraints**:
    - **Specifier**: Must be one of the following: `1`, `X`, or `2`.
    - **Odd Value**: Must be a positive decimal value (e.g., `1.50`, `2.75`).
    - **Unique Odds**: For each match, there must be exactly one specifier of each type (`1`, `X`, `2`).
    - **Match Association**: Odds must be associated with a valid match ID.
    """
)
public class MatchOddController {

    @Autowired
    private final MatchOddService matchOddService;

    public MatchOddController(MatchOddService matchOddService) {
        this.matchOddService = matchOddService;
    }

    @PostMapping("/{matchId}/odds")
    @Operation(
            summary = "Create a new odd for a match",
            description = "Create a new odd associated with a specific match. Provide the match ID and odd details such as specifier and odd value.",
            tags = {"Match Odds Resource"}
    )
    public ResponseEntity<MatchOddDTO> createMatchOdd(
            @PathVariable(value="matchId") Long matchId,
            @Valid @RequestBody MatchOddDTO matchOddDto) {
        return new ResponseEntity<>(matchOddService.createMatchOdd(matchId, matchOddDto), HttpStatus.CREATED);
    }

    @PostMapping("/{matchId}/odds/bulk")
    @Operation(
            summary = "Create multiple odds for a match",
            description = "Create multiple odds associated with a specific match. Provide the match ID and a list of odds details.",
            tags = {"Bulk Create"}
    )
    public ResponseEntity<List<MatchOddDTO>> createMatchOdds(
            @PathVariable Long matchId,
            @Valid @RequestBody List<MatchOddDTO> matchOddDTOS) {
        List<MatchOddDTO> createdMatchOdds = matchOddService.createMatchOdds(matchId, matchOddDTOS);
        return new ResponseEntity<>(createdMatchOdds, HttpStatus.CREATED);
    }

    @GetMapping("/{matchId}/odds")
    @Operation(
            summary = "Retrieve odds for a match",
            description = "Fetch all odds associated with a specific match using the match ID.",
            tags = {"Match Odds Resource"}
    )
    public ResponseEntity<List<MatchOddDTO>> getMatchOddsByMatchId(
            @PathVariable("matchId") Long matchId) {
        return new ResponseEntity<>(matchOddService.getMatchOddsByMatchId(matchId), HttpStatus.OK);
    }

     @GetMapping("/{matchId}/odds/{id}")
     @Operation(
             summary = "Get an odd by ID for a match",
             description = "Retrieve a specific odd associated with a match using the match ID and the odd's unique identifier.",
             tags = {"Match Odds Resource"}
     )
     public ResponseEntity<MatchOddDTO> getMatchOddById(
            @PathVariable(value="matchId") long matchId,
            @PathVariable(value="id") long matchOddId) {
         return new ResponseEntity<>(matchOddService.getMatchOddById(matchId, matchOddId), HttpStatus.OK);
     }

     @PutMapping("/{matchId}/odds/{id}")
     @Operation(
             summary = "Update an odd for a match",
             description = "Update details of an existing odd for a match using the match ID and the odd's unique identifier. Provide the updated specifier and odd value.",
             tags = {"Match Odds Resource"}
     )
     public ResponseEntity<MatchOddDTO> updateMatchOdd(
              @Valid @RequestBody UpdateMatchOddDTO matchOddDto,
              @PathVariable(value="matchId") long matchId,
              @PathVariable(value="id") long matchOddId) {
         return new ResponseEntity<>(matchOddService.updateMatchOdd(matchId, matchOddId, matchOddDto), HttpStatus.OK);
     }

    @DeleteMapping("/{matchId}/odds/{id}")
    @Operation(
            summary = "Delete an odd by ID for a match",
            description = "Delete a specific odd associated with a match using the match ID and the odd's unique identifier.",
            tags = {"Match Odds Resource"}
    )
    public ResponseEntity<String> deleteMatchOddById(
            @PathVariable(value="matchId") long matchId,
            @PathVariable(value="id") long matchOddId) {
        matchOddService.deleteMatchOddById(matchId, matchOddId);
        return ResponseEntity.ok("MatchOdd Entity with id="+ matchOddId +" deleted successfully!");
    }

}
