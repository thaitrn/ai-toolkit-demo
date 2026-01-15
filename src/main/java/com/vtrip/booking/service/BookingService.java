package com.vtrip.booking.service;

import com.vtrip.booking.dto.request.CreateBookingRequestDto;
import com.vtrip.booking.dto.request.UpdateBookingRequestDto;
import com.vtrip.booking.dto.response.BookingResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {

    BookingResponseDto getById(Long id);

    Page<BookingResponseDto> findAll(Pageable pageable);

    BookingResponseDto create(CreateBookingRequestDto request);

    BookingResponseDto update(Long id, UpdateBookingRequestDto request);

    void delete(Long id);
}
