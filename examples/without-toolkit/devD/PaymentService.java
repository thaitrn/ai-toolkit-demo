package com.vtrip.payment.domain;

import com.vtrip.payment.application.PaymentDTO;
import com.vtrip.payment.infrastructure.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Dev D (Trang) - 4 năm exp, senior nhưng mới join team
 * Issues:
 * - RuntimeException instead of BusinessException
 * - Manual Builder mapping (no MapStruct)
 * - No interface
 * - Domain-Driven package structure (inconsistent with team)
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentDTO findPaymentById(Long id) {
        log.info("Finding payment with id: {}", id);
        return paymentRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new RuntimeException("Not found: " + id)); // ❌ RuntimeException!
    }

    public PaymentDTO createPayment(PaymentDTO dto) {
        log.info("Creating payment: {}", dto);
        Payment payment = toEntity(dto);
        Payment saved = paymentRepository.save(payment);
        return toDto(saved);
    }

    public PaymentDTO updatePayment(Long id, PaymentDTO dto) {
        log.info("Updating payment: {}", id);
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found: " + id));

        // Manual update
        payment.setAmount(dto.getAmount());
        payment.setStatus(dto.getStatus());

        return toDto(paymentRepository.save(payment));
    }

    public void deletePayment(Long id) {
        log.info("Deleting payment: {}", id);
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found: " + id);
        }
        paymentRepository.deleteById(id);
    }

    // ❌ Manual mapping, easy to get out of sync with entity
    private PaymentDTO toDto(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .createdAt(payment.getCreatedAt())
                .build();
    }

    private Payment toEntity(PaymentDTO dto) {
        return Payment.builder()
                .amount(dto.getAmount())
                .status(dto.getStatus())
                .build();
    }
}
