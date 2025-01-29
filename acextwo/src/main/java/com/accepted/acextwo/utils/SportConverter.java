package com.accepted.acextwo.utils;

import com.accepted.acextwo.entity.Match;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SportConverter implements AttributeConverter<Match.Sport, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Match.Sport sport) {
        if (sport == null) {
            return null;
        }
        return sport.getSportCode();
    }

    @Override
    public Match.Sport convertToEntityAttribute(Integer sportCode) {
        if (sportCode == null) {
            return null;
        }

        for (Match.Sport sport : Match.Sport.values()) {
            if (sport.getSportCode() == sportCode) {
                return sport;
            }
        }

        throw new IllegalArgumentException("Unknown sport code: " + sportCode);
    }
}
