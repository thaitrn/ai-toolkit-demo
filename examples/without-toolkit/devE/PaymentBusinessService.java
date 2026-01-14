package com.vtrip.payment.business;

import com.vtrip.payment.data.PaymentDataRepository;
import com.vtrip.payment.data.PaymentEntity;
import com.vtrip.payment.exception.ResourceNotFoundException;
import com.vtrip.payment.transfer.PaymentTransferObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Dev E (Nam) - 2 năm exp, mid-level
 * Issues:
 * - Field injection
 * - Inconsistent naming (TransferObject, BusinessService, etc.)
 * - Static factory method for mapping (not standard)
 * - Custom exception but different from team standard
 */
@Service
public class PaymentBusinessService {

    @Autowired // ❌ Field injection!
    PaymentDataRepository repository;

    public PaymentTransferObject get(Long id) {
        PaymentEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", id)); // ❌ Different exception type!
        return PaymentTransferObject.from(entity); // ❌ Static factory, not MapStruct
    }

    public PaymentTransferObject create(PaymentTransferObject to) {
        PaymentEntity entity = to.toEntity(); // ❌ Method on DTO, not mapper
        PaymentEntity saved = repository.save(entity);
        return PaymentTransferObject.from(saved);
    }

    public PaymentTransferObject update(Long id, PaymentTransferObject to) {
        PaymentEntity entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", id));

        entity.setAmount(to.getAmount());
        entity.setStatus(to.getStatus());

        return PaymentTransferObject.from(repository.save(entity));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Payment", id);
        }
        repository.deleteById(id);
    }
}
