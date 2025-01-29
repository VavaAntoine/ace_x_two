package com.accepted.acextwo.dto;

import java.util.Date;

public record ErrorDetails(Date timestamp, String message, String details) {

}
