package com.vtrip.payment.services;

import com.vtrip.payment.dtos.PaymentDTO;
import com.vtrip.payment.entities.PaymentEntity;
import com.vtrip.payment.repos.PaymentRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Dev C (Hùng) - 2 năm exp, từ NodeJS chuyển sang
 * Issues:
 * - Generic Exception instead of BusinessException
 * - BeanUtils.copyProperties (no compile-time safety)
 * - Inconsistent naming (repos, entities, dtos - all plural)
 * - Entity suffix on domain class
 */
@Service
@Transactional
public class PaymentServiceImpl {

    private final PaymentRepo repo;

    public PaymentServiceImpl(PaymentRepo repo) {
        this.repo = repo;
    }

    public PaymentDTO getPayment(Long id) throws Exception { // ❌ Generic Exception!
        Optional<PaymentEntity> opt = repo.findById(id);
        if (!opt.isPresent()) {
            throw new Exception("Payment not found"); // ❌ Generic Exception!
        }
        return mapToDTO(opt.get());
    }

    public PaymentDTO createPayment(PaymentDTO dto) throws Exception {
        PaymentEntity entity = mapToEntity(dto);
        PaymentEntity saved = repo.save(entity);
        return mapToDTO(saved);
    }

    public PaymentDTO updatePayment(Long id, PaymentDTO dto) throws Exception {
        Optional<PaymentEntity> opt = repo.findById(id);
        if (!opt.isPresent()) {
            throw new Exception("Payment not found for update");
        }
        PaymentEntity entity = opt.get();
        BeanUtils.copyProperties(dto, entity, "id"); // ❌ No compile-time safety!
        return mapToDTO(repo.save(entity));
    }

    public void deletePayment(Long id) throws Exception {
        if (!repo.existsById(id)) {
            throw new Exception("Payment not found for delete");
        }
        repo.deleteById(id);
    }

    private PaymentDTO mapToDTO(PaymentEntity entity) {
        PaymentDTO dto = new PaymentDTO();
        BeanUtils.copyProperties(entity, dto); // ❌ No compile-time safety!
        return dto;
    }

    private PaymentEntity mapToEntity(PaymentDTO dto) {
        PaymentEntity entity = new PaymentEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
