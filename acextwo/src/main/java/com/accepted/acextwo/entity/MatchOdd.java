package com.accepted.acextwo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "match_odd", schema = "fb_bb_match_mgt")
public class MatchOdd {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_odd_id_seq")
    @SequenceGenerator(
            name = "match_odd_id_seq", schema = "fb_bb_match_mgt",
            sequenceName = "match_odd_id_seq",
            allocationSize = 1
    )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @Column(nullable = false, length = 1) // Specifier: single char like 'X'
    private String specifier;

    @Column(nullable = false)
    private Double odd;

    public MatchOdd() {
    }

    public MatchOdd(Long id, Match match, String specifier, Double odd) {
        this.id = id;
        this.match = match;
        this.specifier = specifier;
        this.odd = odd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public String getSpecifier() {
        return specifier;
    }

    public void setSpecifier(String specifier) {
        this.specifier = specifier;
    }

    public Double getOdd() {
        return odd;
    }

    public void setOdd(Double odd) {
        this.odd = odd;
    }
}
