package com.vtrip.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Dev A (Minh) - 3 năm exp
 * Issues:
 * - Field injection (@Autowired)
 * - Returns null instead of throwing exception
 * - Manual mapping, easy to miss fields
 * - No interface
 * - No logging
 * - No @Transactional
 */
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repo;

    public Payment getById(Long id) {
        return repo.findById(id).orElse(null); // ❌ Returns null!
    }

    public Payment create(PaymentDTO dto) {
        Payment p = new Payment();
        p.setAmount(dto.getAmount());
        p.setStatus(dto.getStatus());
        // ❌ Forgot createdBy, currency, etc.
        return repo.save(p);
    }

    public Payment update(Long id, PaymentDTO dto) {
        Payment p = repo.findById(id).orElse(null);
        if (p == null) {
            return null; // ❌ Returns null!
        }
        p.setAmount(dto.getAmount());
        return repo.save(p);
    }

    public void delete(Long id) {
        repo.deleteById(id); // ❌ No check if exists
    }
}
