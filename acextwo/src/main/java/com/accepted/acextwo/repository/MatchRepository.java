package com.accepted.acextwo.repository;

import com.accepted.acextwo.entity.Match;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query("""
        SELECT m
        FROM Match m
        WHERE m.sport = :sport
          AND m.matchDate BETWEEN :startDate AND :endDate
          AND (m.teamA = :teamA OR m.teamB = :teamA OR m.teamA = :teamB OR m.teamB = :teamB)
    """)
    List<Match> findConflictingMatches(
            @Param("sport") Match.Sport sport,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("teamA") String teamA,
            @Param("teamB") String teamB
    );

    @EntityGraph(attributePaths = {"matchOdds"})
    List<Match> findAll();

    @EntityGraph(attributePaths = {"matchOdds"})
    Optional<Match> findById(Long id);

}
