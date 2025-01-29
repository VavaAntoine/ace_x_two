package com.accepted.acextwo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RelationMismatchException extends RuntimeException {
    private final String sourceEntity;
    private final String targetEntity;
    private final String mismatchReason;

    public String getSourceEntity() {
        return sourceEntity;
    }

    public String getTargetEntity() {
        return targetEntity;
    }

    public String getMismatchReason() {
        return mismatchReason;
    }

    public RelationMismatchException(String sourceEntity, String targetEntity, String mismatchReason) {
        super(String.format("Invalid relation between %s and %s: %s", sourceEntity, targetEntity, mismatchReason));
        this.sourceEntity = sourceEntity;
        this.targetEntity = targetEntity;
        this.mismatchReason = mismatchReason;
    }
}