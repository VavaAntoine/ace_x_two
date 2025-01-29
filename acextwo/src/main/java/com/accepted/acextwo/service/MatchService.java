package com.accepted.acextwo.service;

import com.accepted.acextwo.dto.MatchDTO;
import com.accepted.acextwo.dto.MatchWithOddsDTO;

import java.util.List;

public interface MatchService {
    MatchDTO createMatch(MatchDTO matchDto);
    List<MatchDTO> createMatches(List<MatchDTO> matchDTOS);
    MatchWithOddsDTO createMatchWithOdds(MatchWithOddsDTO matchWithOddsDTO);
    List<MatchWithOddsDTO> getAllMatches();
    MatchWithOddsDTO getMatchById(long matchId);
    MatchDTO updateMatch(MatchDTO matchDto, long matchId);
    void deleteMatchById(long matchId);
}
