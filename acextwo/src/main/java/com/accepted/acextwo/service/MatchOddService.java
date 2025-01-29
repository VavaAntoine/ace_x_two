package com.accepted.acextwo.service;

import com.accepted.acextwo.dto.MatchOddDTO;
import com.accepted.acextwo.dto.UpdateMatchOddDTO;

import java.util.List;

public interface MatchOddService {
    List<MatchOddDTO> getMatchOddsByMatchId(Long matchId);
    MatchOddDTO createMatchOdd(Long matchId, MatchOddDTO matchOddDto);
    List<MatchOddDTO> createMatchOdds(Long matchId, List<MatchOddDTO> matchOddDTOS);
    MatchOddDTO getMatchOddById(Long matchId, Long matchOddId);
    MatchOddDTO updateMatchOdd(Long matchId, Long matchOddId, UpdateMatchOddDTO matchOddDTOReq);
    void deleteMatchOddById(Long matchId, Long matchOddId);

}
