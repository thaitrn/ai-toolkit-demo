package com.vtrip.payment.service;

import com.vtrip.payment.dto.PaymentDto;
import com.vtrip.payment.model.Payment;
import com.vtrip.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Dev B (Linh) - 1 năm exp, junior
 * Issues:
 * - .get() without check → NoSuchElementException
 * - Manual mapping with missing fields
 * - No interface
 * - Inconsistent naming (PaymentDto vs PaymentDTO)
 */
@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentDto findById(Long id) {
        Payment payment = paymentRepository.findById(id).get(); // ❌ NoSuchElementException!
        return convertToDto(payment);
    }

    public PaymentDto create(PaymentDto dto) {
        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        payment.setStatus(dto.getStatus());
        Payment saved = paymentRepository.save(payment);
        return convertToDto(saved);
    }

    private PaymentDto convertToDto(Payment payment) {
        PaymentDto dto = new PaymentDto();
        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        // ❌ Forgot status, createdAt, etc.!
        return dto;
    }

    private Payment convertToEntity(PaymentDto dto) {
        Payment payment = new Payment();
        payment.setAmount(dto.getAmount());
        // ❌ Incomplete conversion
        return payment;
    }
}
