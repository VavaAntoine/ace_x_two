package com.accepted.acextwo.deserializer;

import com.accepted.acextwo.entity.Match;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class SportDeserializer extends JsonDeserializer<Match.Sport> {

    @Override
    public Match.Sport deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();

        if ("1".equals(value) || "Football".equalsIgnoreCase(value)) {
            return Match.Sport.Football;
        } else if ("2".equals(value) || "Basketball".equalsIgnoreCase(value)) {
            return Match.Sport.Basketball;
        }

        throw new IllegalArgumentException(
                String.format("Invalid value '%s' for Sport. Allowed values: 1, 2, Football, Basketball.", value)
        );
    }
}
