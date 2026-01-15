package com.vtrip.booking.controller;

import com.vtrip.booking.dto.request.CreateBookingRequestDto;
import com.vtrip.booking.dto.request.UpdateBookingRequestDto;
import com.vtrip.booking.dto.response.BookingResponseDto;
import com.vtrip.booking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@Tag(name = "Bookings", description = "Booking management APIs")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @Operation(summary = "Get booking by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Booking found"),
        @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getById(
            @Parameter(description = "Booking ID") @PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getById(id));
    }

    @Operation(summary = "List all bookings with pagination")
    @GetMapping
    public ResponseEntity<Page<BookingResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(bookingService.findAll(pageable));
    }

    @Operation(summary = "Create new booking")
    @ApiResponse(responseCode = "201", description = "Booking created successfully")
    @PostMapping
    public ResponseEntity<BookingResponseDto> create(
            @Valid @RequestBody CreateBookingRequestDto request) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(bookingService.create(request));
    }

    @Operation(summary = "Update booking")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Booking updated"),
        @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDto> update(
            @Parameter(description = "Booking ID") @PathVariable Long id,
            @Valid @RequestBody UpdateBookingRequestDto request) {
        return ResponseEntity.ok(bookingService.update(id, request));
    }

    @Operation(summary = "Delete booking")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Booking deleted"),
        @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Booking ID") @PathVariable Long id) {
        bookingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
