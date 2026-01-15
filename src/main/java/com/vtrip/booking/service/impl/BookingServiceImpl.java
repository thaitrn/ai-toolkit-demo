package com.vtrip.booking.service.impl;

import com.vtrip.booking.dto.request.CreateBookingRequestDto;
import com.vtrip.booking.dto.request.UpdateBookingRequestDto;
import com.vtrip.booking.dto.response.BookingResponseDto;
import com.vtrip.booking.exception.NotFoundException;
import com.vtrip.booking.mapper.BookingMapper;
import com.vtrip.booking.repository.BookingRepository;
import com.vtrip.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    @Transactional(readOnly = true)
    public BookingResponseDto getById(Long id) {
        log.debug("Finding booking with id: {}", id);
        return bookingRepository.findById(id)
            .map(bookingMapper::toResponseDto)
            .orElseThrow(() -> NotFoundException.forEntity("Booking", id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingResponseDto> findAll(Pageable pageable) {
        log.debug("Finding all bookings with pageable: {}", pageable);
        return bookingRepository.findAll(pageable)
            .map(bookingMapper::toResponseDto);
    }

    @Override
    @Transactional
    public BookingResponseDto create(CreateBookingRequestDto request) {
        log.info("Creating new booking: {}", request);
        var entity = bookingMapper.toEntity(request);
        var saved = bookingRepository.save(entity);
        log.info("Created booking with id: {}", saved.getId());
        return bookingMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public BookingResponseDto update(Long id, UpdateBookingRequestDto request) {
        log.info("Updating booking with id: {}", id);
        var entity = bookingRepository.findById(id)
            .orElseThrow(() -> NotFoundException.forEntity("Booking", id));
        bookingMapper.updateEntityFromDto(entity, request);
        var saved = bookingRepository.save(entity);
        log.info("Updated booking with id: {}", saved.getId());
        return bookingMapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting booking with id: {}", id);
        if (!bookingRepository.existsById(id)) {
            throw NotFoundException.forEntity("Booking", id);
        }
        bookingRepository.deleteById(id);
        log.info("Deleted booking with id: {}", id);
    }
}
