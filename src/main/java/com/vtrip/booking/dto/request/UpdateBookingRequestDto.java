package com.vtrip.booking.dto.request;

import com.vtrip.booking.entity.BookingStatus;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookingRequestDto {

    @Future(message = "Check-in date must be in the future")
    private LocalDate checkInDate;

    @Future(message = "Check-out date must be in the future")
    private LocalDate checkOutDate;

    @Positive(message = "Total amount must be positive")
    private BigDecimal totalAmount;

    private BookingStatus status;
}
