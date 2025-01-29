package com.accepted.acextwo.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class MatchOddDTO {
//    private Long id;

    @NotNull(message = "Specifier is required")
    @Pattern(regexp = "[12X]", message = "Specifier must be one of the following values: 1, 2, X")
    private String specifier;

    @NotNull(message = "Odd value is required")
    @DecimalMin(value = "1.00", inclusive = true, message = "Odd must be at least 1.00")
    @DecimalMax(value = "100.00", inclusive = true, message = "Odd must not exceed 100.00")
    private Double odd;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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
