package com.vtrip.payment.service.impl;

import com.vtrip.payment.dto.request.CreatePaymentRequestDto;
import com.vtrip.payment.dto.request.UpdatePaymentRequestDto;
import com.vtrip.payment.dto.response.PaymentResponseDto;
import com.vtrip.payment.entity.Payment;
import com.vtrip.payment.exception.NotFoundException;
import com.vtrip.payment.mapper.PaymentMapper;
import com.vtrip.payment.repository.PaymentRepository;
import com.vtrip.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ✅ WITH AI TOOLKIT - All 5 developers write EXACTLY this code
 * 
 * Enforced by:
 * - 01-core-architecture.mdc: Package structure, interface pattern, DI
 * - 04-mapstruct-mapper.mdc: MapStruct for mapping
 * - 06-error-handling.mdc: NotFoundException extends BusinessException
 * 
 * Benefits:
 * - ✅ Constructor injection via @RequiredArgsConstructor
 * - ✅ Interface + Implementation pattern
 * - ✅ Consistent exception handling (NotFoundException)
 * - ✅ MapStruct for type-safe mapping
 * - ✅ Proper @Transactional usage
 * - ✅ Consistent logging with @Slf4j
 * - ✅ Standard method naming
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    @Transactional(readOnly = true)
    public PaymentResponseDto getById(Long id) {
        log.debug("Finding payment with id: {}", id);

        return paymentRepository.findById(id)
                .map(paymentMapper::toResponseDto)
                .orElseThrow(() -> NotFoundException.forEntity("Payment", id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PaymentResponseDto> findAll(Pageable pageable) {
        log.debug("Finding all payments with pageable: {}", pageable);

        return paymentRepository.findAll(pageable)
                .map(paymentMapper::toResponseDto);
    }

    @Override
    @Transactional
    public PaymentResponseDto create(CreatePaymentRequestDto request) {
        log.info("Creating new payment: {}", request);

        var entity = paymentMapper.toEntity(request);
        var saved = paymentRepository.save(entity);

        log.info("Created payment with id: {}", saved.getId());
        return paymentMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public PaymentResponseDto update(Long id, UpdatePaymentRequestDto request) {
        log.info("Updating payment with id: {}", id);

        var entity = paymentRepository.findById(id)
                .orElseThrow(() -> NotFoundException.forEntity("Payment", id));

        paymentMapper.updateEntityFromDto(entity, request);
        var saved = paymentRepository.save(entity);

        log.info("Updated payment with id: {}", saved.getId());
        return paymentMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting payment with id: {}", id);

        if (!paymentRepository.existsById(id)) {
            throw NotFoundException.forEntity("Payment", id);
        }

        paymentRepository.deleteById(id);
        log.info("Deleted payment with id: {}", id);
    }
}
