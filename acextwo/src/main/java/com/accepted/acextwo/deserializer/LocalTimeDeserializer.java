package com.accepted.acextwo.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {

    @Override
    public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    JsonNode node = p.readValueAs(JsonNode.class);

    if (node.isTextual()) {
        String timeString = node.asText();
        try {
            return LocalTime.parse(timeString); // Parse standard time format
        } catch (DateTimeParseException e) {
            throw new IOException("Invalid time format. Expected HH:mm:ss or structured JSON.");
        }
    }

    if (node.isObject()) {
        int hour = node.has("hour") ? node.get("hour").asInt() : 0;
        int minute = node.has("minute") ? node.get("minute").asInt() : 0;
        int second = node.has("second") ? node.get("second").asInt() : 0;
        int nano = node.has("nano") ? node.get("nano").asInt() : 0;
        return LocalTime.of(hour, minute, second, nano);
    }

    throw new IOException("Invalid time format. Expected HH:mm:ss or structured JSON.");
}
}