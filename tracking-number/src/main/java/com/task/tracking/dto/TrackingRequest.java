package com.task.tracking.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@ToString
public class TrackingRequest {

    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$", message = "origin_country_id must be a 2-letter ISO code")
    private String origin_country_id;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$", message = "destination_country_id must be a 2-letter ISO code")
    private String destination_country_id;

    @NotNull
    @Digits(integer = 5, fraction = 3)
    @DecimalMin(value = "0.001", inclusive = true)
    private BigDecimal weight;

    @NotNull(message = "created_at is required")
    private String created_at;

    @NotNull
    private UUID customer_id;

    @NotBlank
    private String customer_name;

    @NotBlank
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "customer_slug must be in kebab-case")
    private String customer_slug;
}

