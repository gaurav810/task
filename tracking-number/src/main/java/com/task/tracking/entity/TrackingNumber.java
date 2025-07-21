package com.task.tracking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "tracking_number", uniqueConstraints = {
        @UniqueConstraint(columnNames = "tracking_number")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "origin_country_id", nullable = false, length = 2)
    private String originCountryId;

    @Column(name = "destination_country_id", nullable = false, length = 2)
    private String destinationCountryId;

    @Column(name = "weight", nullable = false, precision = 10, scale = 3)
    private BigDecimal weight;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(name = "customer_name", nullable = false, length = 255)
    private String customerName;

    @Column(name = "customer_slug", nullable = false, length = 255)
    private String customerSlug;

    @Column(name = "tracking_number", nullable = false, length = 16, unique = true)
    private String trackingNumber;

    @Column(name = "generated_at", nullable = false, updatable = false)
    private OffsetDateTime generatedAt;
}
