package com.accepted.acextwo.service.impl;

import com.accepted.acextwo.dto.MatchDTO;
import com.accepted.acextwo.dto.MatchOddDTO;
import com.accepted.acextwo.dto.MatchWithOddsDTO;
import com.accepted.acextwo.entity.Match;
import com.accepted.acextwo.entity.MatchOdd;
import com.accepted.acextwo.exception.InvalidMatchException;
import com.accepted.acextwo.exception.ResourceNotFoundException;
import com.accepted.acextwo.repository.MatchOddRepository;
import com.accepted.acextwo.repository.MatchRepository;
import com.accepted.acextwo.service.MatchService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl implements MatchService {

    private final ModelMapper modelMapper;
    private final MatchRepository matchRepository;
    private final MatchOddRepository matchOddRepository;

    public MatchServiceImpl(
            ModelMapper modelMapper,
            MatchRepository matchRepository,
            MatchOddRepository matchOddRepository) {
        this.modelMapper = modelMapper;
        this.matchRepository = matchRepository;
        this.matchOddRepository = matchOddRepository;
    }

    @Override
    public MatchDTO createMatch(MatchDTO matchDto) {
        validateMatchCreation(matchDto);
        Match match = mapToEntity(matchDto);
        Match newMatch = matchRepository.save(match);
        return mapToDTO(newMatch);
    }

    @Override
    public MatchWithOddsDTO createMatchWithOdds(MatchWithOddsDTO matchWithOddsDTO) {

        MatchDTO matchDTO = modelMapper.map(matchWithOddsDTO, MatchDTO.class);
        validateMatchCreation(matchDTO);
        validateOdds(matchWithOddsDTO.getMatchOdds());

        Match match = mapToEntity(matchDTO);
        Match savedMatch = matchRepository.save(match);

        for (MatchOddDTO oddDTO : matchWithOddsDTO.getMatchOdds()) {
            MatchOdd odd = new MatchOdd();
            odd.setSpecifier(oddDTO.getSpecifier());
            odd.setOdd(oddDTO.getOdd());
            odd.setMatch(savedMatch);
            matchOddRepository.save(odd);
        }

        Long id = savedMatch.getId();
        savedMatch = matchRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Match", "id", id));

        return mapToResponseDTO(savedMatch);
    }

    @Override
    public List<MatchDTO> createMatches(List<MatchDTO> matchDTOS) {
        List<MatchDTO> createdMatches = new ArrayList<>();

        for (MatchDTO matchDto : matchDTOS) {
            validateMatchCreation(matchDto);
            Match match = mapToEntity(matchDto);
            Match savedMatch = matchRepository.save(match);
            createdMatches.add(mapToDTO(savedMatch));
        }
        return createdMatches;
    }

    @Override
    public List<MatchWithOddsDTO> getAllMatches() {
        List<Match> allMatches = matchRepository.findAll();
        return allMatches.stream()
                .map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public MatchWithOddsDTO getMatchById(long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(()->new ResourceNotFoundException("Match", "id", matchId));
        return mapToResponseDTO(match);
    }

    @Transactional
    @Override
    public MatchDTO updateMatch(MatchDTO matchDto, long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(()->new ResourceNotFoundException("Match", "id", matchId));
        modelMapper.map(matchDto,match);
        match.setId(matchId);
        Match updatedMatch = matchRepository.save(match);
        return mapToDTO(updatedMatch);
    }

    @Override
    public void deleteMatchById(long matchId) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(()->new ResourceNotFoundException("Match", "id", matchId));
        matchRepository.delete(match);
    }

    private MatchDTO mapToDTO(Match match) {
        return modelMapper.map(match, MatchDTO.class);
    }

    private MatchWithOddsDTO mapToResponseDTO(Match match) {
        return modelMapper.map(match, MatchWithOddsDTO.class);
    }

    private Match mapToEntity(MatchDTO matchDto) {
        return modelMapper.map(matchDto,Match.class);
    }

    private void validateMatchCreation(MatchDTO matchDto) {
        LocalDate matchDate = matchDto.getMatchDate();
        LocalDate startDate = matchDate.minusDays(2);
        LocalDate endDate = matchDate.plusDays(2);

        List<Match> conflictingMatches = matchRepository.findConflictingMatches(
                matchDto.getSport(),
                startDate,
                endDate,
                matchDto.getTeamA(),
                matchDto.getTeamB()
        );

        if (!conflictingMatches.isEmpty()) {
            throw new InvalidMatchException(
                    "Teams must rest for 2 days between matches in the same sport."
            );
        }
    }

    private void validateOdds(List<MatchOddDTO> odds) {
        // Check if exactly 3 odds are provided
        if (odds.size() != 3) {
            throw new IllegalArgumentException("Exactly 3 odds must be provided.");
        }

        Set<String> specifiers = odds.stream()
                .map(MatchOddDTO::getSpecifier)
                .collect(Collectors.toSet());

        Set<String> requiredSpecifiers = Set.of("1", "X", "2");
        if (!specifiers.equals(requiredSpecifiers)) {
            throw new IllegalArgumentException("Specifiers must be distinct and include exactly '1', 'X', and '2'.");
        }
    }

}
