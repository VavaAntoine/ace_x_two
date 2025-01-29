package com.accepted.acextwo.repository;

import com.accepted.acextwo.entity.MatchOdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MatchOddRepository extends JpaRepository<MatchOdd,Long> {
    List<MatchOdd> findByMatchId(Long matchId);

    @Query("SELECT mo.specifier FROM MatchOdd mo WHERE mo.match.id = :matchId")
    List<String> findSpecifiersByMatchId(@Param("matchId") Long matchId);

    boolean existsBySpecifierAndMatchId(String specifier, Long matchId);

    Optional<MatchOdd> findBySpecifierAndMatchId(String specifier, Long matchId);
}
