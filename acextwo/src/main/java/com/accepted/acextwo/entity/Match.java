package com.accepted.acextwo.entity;

import com.accepted.acextwo.deserializer.SportDeserializer;
import com.accepted.acextwo.utils.SportConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "match", schema = "fb_bb_match_mgt")
public class Match {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_id_seq")
        @SequenceGenerator(
                name = "match_id_seq", schema = "fb_bb_match_mgt",
                sequenceName = "match_id_seq",
                allocationSize = 1
        )
        @Column(name = "id", updatable = false, nullable = false)
        private Long id;

        @Column(name = "description", nullable = false)
        private String description;

        @Column(name = "match_date", nullable = false)
        private LocalDate matchDate;

        @Column(name = "match_time", nullable = false)
        private LocalTime matchTime;

        @Column(name = "team_a", nullable = false)
        private String teamA;

        @Column(name = "team_b", nullable = false)
        private String teamB;

        @Convert(converter = SportConverter.class)
        @Column(name = "sport", nullable = false)
        private Sport sport;

        @OneToMany(mappedBy = "match", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<MatchOdd> matchOdds = new ArrayList<>();

        public enum Sport {
                Football(1),
                Basketball(2);

                private final int sportCode;

                Sport(int sportCode) {
                        this.sportCode = sportCode;
                }

                public int getSportCode() {
                        return sportCode;
                }
        }

        public Match() {
        }

        public Match(Long id, String description, LocalDate matchDate, LocalTime matchTime, String teamA, String teamB, Sport sport, List<MatchOdd> matchOdds) {
                this.id = id;
                this.description = description;
                this.matchDate = matchDate;
                this.matchTime = matchTime;
                this.teamA = teamA;
                this.teamB = teamB;
                this.sport = sport;
                this.matchOdds = matchOdds;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public LocalDate getMatchDate() {
                return matchDate;
        }

        public void setMatchDate(LocalDate matchDate) {
                this.matchDate = matchDate;
        }

        public LocalTime getMatchTime() {
                return matchTime;
        }

        public void setMatchTime(LocalTime matchTime) {
                this.matchTime = matchTime;
        }

        public String getTeamA() {
                return teamA;
        }

        public void setTeamA(String teamA) {
                this.teamA = teamA;
        }

        public String getTeamB() {
                return teamB;
        }

        public void setTeamB(String teamB) {
                this.teamB = teamB;
        }

        public Sport getSport() {
                return sport;
        }

        public void setSport(Sport sport) {
                this.sport = sport;
        }

        public List<MatchOdd> getMatchOdds() {
                return matchOdds;
        }

        public void setMatchOdds(List<MatchOdd> matchOdds) {
                this.matchOdds = matchOdds;
        }

}
