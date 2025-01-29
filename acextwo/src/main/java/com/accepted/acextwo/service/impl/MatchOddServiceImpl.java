package com.accepted.acextwo.service.impl;

import com.accepted.acextwo.dto.MatchOddDTO;
import com.accepted.acextwo.dto.UpdateMatchOddDTO;
import com.accepted.acextwo.entity.Match;
import com.accepted.acextwo.entity.MatchOdd;
import com.accepted.acextwo.exception.RelationMismatchException;
import com.accepted.acextwo.exception.ResourceNotFoundException;
import com.accepted.acextwo.repository.MatchOddRepository;
import com.accepted.acextwo.repository.MatchRepository;
import com.accepted.acextwo.service.MatchOddService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MatchOddServiceImpl implements MatchOddService {

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final MatchOddRepository matchOddRepository;

    @Autowired
    private final MatchRepository matchRepository;

    public MatchOddServiceImpl(ModelMapper modelMapper, MatchOddRepository matchOddRepository, MatchRepository matchRepository) {
        this.modelMapper = modelMapper;
        this.matchOddRepository = matchOddRepository;
        this.matchRepository = matchRepository;
    }

    @Override
    public MatchOddDTO createMatchOdd(Long matchId, MatchOddDTO matchOddDto) {
        Match match = matchRepository.findById(matchId).orElseThrow(
                ()->new ResourceNotFoundException("Match","id",matchId));
        boolean exists = matchOddRepository.existsBySpecifierAndMatchId(matchOddDto.getSpecifier(), matchId);
        if (exists) {
            throw new IllegalArgumentException("Specifier already exists for this match. Cannot create duplicate odds.");
        }
        MatchOdd matchOdd = mapToEntity(matchOddDto);
        matchOdd.setMatch(match);
        MatchOdd newMatchOdd = matchOddRepository.save(matchOdd);
        return mapToDTO(newMatchOdd);
    }

    @Override
    public List<MatchOddDTO> createMatchOdds(Long matchId, List<MatchOddDTO> matchOddDTOS) {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new ResourceNotFoundException("Match", "id", matchId));

        Set<String> specifiersFromDTO = matchOddDTOS.stream()
                .map(MatchOddDTO::getSpecifier)
                .collect(Collectors.toSet());

        if (specifiersFromDTO.size() != matchOddDTOS.size()) {
            throw new IllegalArgumentException("Specifiers must be distinct, duplicates are not allowed.");
        }

        List<String> existingSpecifiers = matchOddRepository.findSpecifiersByMatchId(matchId);

        List<String> conflictingSpecifiers = matchOddDTOS.stream()
                .map(MatchOddDTO::getSpecifier)
                .filter(existingSpecifiers::contains)
                .toList();

        if (!conflictingSpecifiers.isEmpty()) {
            throw new IllegalArgumentException("The following specifiers already exist for this match: " + conflictingSpecifiers);
        }

        List<MatchOddDTO> createdMatchOdds = new ArrayList<>();
        for (MatchOddDTO matchOddDto : matchOddDTOS) {
            MatchOdd matchOdd = mapToEntity(matchOddDto);
            matchOdd.setMatch(match);
            MatchOdd savedMatchOdd = matchOddRepository.save(matchOdd);
            createdMatchOdds.add(mapToDTO(savedMatchOdd));
        }

        return createdMatchOdds;
    }

    @Override
    public List<MatchOddDTO> getMatchOddsByMatchId(Long matchId) {
        List<MatchOdd> matchOdds = matchOddRepository.findByMatchId(matchId);
        return matchOdds.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public MatchOddDTO getMatchOddById(Long matchId, Long matchOddId) {
        Match match = matchRepository.findById(matchId).orElseThrow(
                ()->new ResourceNotFoundException("Match","id",matchId));
        MatchOdd matchOdd = matchOddRepository.findById(matchOddId).orElseThrow(
                ()->new ResourceNotFoundException("MatchOdd","id",matchOddId));
        if(!matchOdd.getMatch().getId().equals(match.getId())){
            throw new RelationMismatchException("Match","MatchOdd","MatchOdd does not belong to match");
        }
        return mapToDTO(matchOdd);
    }

    @Override
    public MatchOddDTO updateMatchOdd(Long matchId, Long matchOddId, UpdateMatchOddDTO matchOddDto) {
        Match match = matchRepository.findById(matchId).orElseThrow(
                ()->new ResourceNotFoundException("Match","id",matchId));
        MatchOdd matchOdd = matchOddRepository.findById(matchOddId).orElseThrow(
                ()->new ResourceNotFoundException("MatchOdd","id",matchOddId));
        if(!matchOdd.getMatch().getId().equals(match.getId())){
            throw new RelationMismatchException("Match","MatchOdd","MatchOdd does not belong to match");
        }
        matchOdd.setOdd(matchOddDto.getOdd());
        MatchOdd updatedMatchOdd = matchOddRepository.save(matchOdd);
        return mapToDTO(updatedMatchOdd);
    }

    @Override
    public void deleteMatchOddById(Long matchId, Long matchOddId) {
        Match match = matchRepository.findById(matchId).orElseThrow(
                ()->new ResourceNotFoundException("Match","id",matchId));
        MatchOdd matchOdd = matchOddRepository.findById(matchOddId).orElseThrow(
                ()->new ResourceNotFoundException("MatchOdd","id",matchOddId));
        if(!matchOdd.getMatch().getId().equals(match.getId())){
            throw new RelationMismatchException("Match","MatchOdd","MatchOdd does not belong to match");
        }
        matchOddRepository.delete(matchOdd);
    }

    public MatchOddDTO mapToDTO(MatchOdd matchOdd) {
        return modelMapper.map(matchOdd, MatchOddDTO.class);
    }

    public MatchOdd mapToEntity(MatchOddDTO matchOddDto) {
        return modelMapper.map(matchOddDto, MatchOdd.class);
    }

}
