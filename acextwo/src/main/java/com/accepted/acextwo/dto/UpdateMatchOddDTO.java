package com.accepted.acextwo.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UpdateMatchOddDTO {

    @NotNull(message = "Odd value is required")
    @DecimalMin(value = "1.00", inclusive = true, message = "Odd must be at least 1.00")
    @DecimalMax(value = "100.00", inclusive = true, message = "Odd must not exceed 100.00")
    private Double odd;

    public Double getOdd() {
        return odd;
    }

    public void setOdd(Double odd) {
        this.odd = odd;
    }
}
