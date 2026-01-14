package com.vtrip.payment.controller;

import com.vtrip.payment.dto.request.CreatePaymentRequestDto;
import com.vtrip.payment.dto.request.UpdatePaymentRequestDto;
import com.vtrip.payment.dto.response.PaymentResponseDto;
import com.vtrip.payment.service.PaymentService;
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

/**
 * ✅ WITH AI TOOLKIT - All 5 developers write EXACTLY this code
 * 
 * Enforced by:
 * - 01-core-architecture.mdc: Package structure, DI pattern
 * - 07-api-documentation.mdc: OpenAPI annotations
 * 
 * Features:
 * - ✅ Consistent path: /api/v1/{entities}
 * - ✅ Full OpenAPI documentation
 * - ✅ @Valid for input validation
 * - ✅ Correct HTTP status codes (201 for create, 204 for delete)
 * - ✅ ResponseEntity wrapper
 * - ✅ Consistent parameter annotations
 */
@RestController
@RequestMapping("/api/v1/payments")
@Tag(name = "Payments", description = "Payment management APIs")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Get payment by ID", description = "Retrieves a payment by its unique identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment found"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> getById(
            @Parameter(description = "Payment ID", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getById(id));
    }

    @Operation(summary = "List all payments with pagination")
    @GetMapping
    public ResponseEntity<Page<PaymentResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok(paymentService.findAll(pageable));
    }

    @Operation(summary = "Create new payment")
    @ApiResponse(responseCode = "201", description = "Payment created successfully")
    @PostMapping
    public ResponseEntity<PaymentResponseDto> create(
            @Valid @RequestBody CreatePaymentRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(paymentService.create(request));
    }

    @Operation(summary = "Update payment")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Payment updated"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> update(
            @Parameter(description = "Payment ID") @PathVariable Long id,
            @Valid @RequestBody UpdatePaymentRequestDto request) {
        return ResponseEntity.ok(paymentService.update(id, request));
    }

    @Operation(summary = "Delete payment")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Payment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Payment ID") @PathVariable Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
