package com.vtrip.{servicename}.dto.response;

import com.vtrip.{servicename}.entity.{Entity}Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class {Entity}ResponseDto {

    private Long id;
    private Long customerId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BigDecimal totalAmount;
    private {Entity}Status status;
    private Instant createdAt;
    private Instant updatedAt;
}
