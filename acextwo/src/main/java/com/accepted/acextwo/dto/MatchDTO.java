package com.accepted.acextwo.dto;

import com.accepted.acextwo.deserializer.LocalTimeDeserializer;
import com.accepted.acextwo.deserializer.SportDeserializer;
import com.accepted.acextwo.entity.Match;
import com.accepted.acextwo.validator.ValidSport;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchDTO {
//    private Long id;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @NotNull(message = "Match date is required")
    @FutureOrPresent(message = "Match date cannot be in the past")
    private LocalDate matchDate;

    @NotNull(message = "Match time is required")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime matchTime;

    @NotBlank(message = "Team A cannot be blank")
    @Size(max = 100, message = "Team A name must not exceed 100 characters")
    private String teamA;

    @NotBlank(message = "Team B cannot be blank")
    @Size(max = 100, message = "Team B name must not exceed 100 characters")
    private String teamB;

    @ValidSport
    @JsonDeserialize(using = SportDeserializer.class)
    private Match.Sport sport;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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

    public Match.Sport getSport() {
        return sport;
    }

    public void setSport(Match.Sport sport) {
        this.sport = sport;
    }

}
