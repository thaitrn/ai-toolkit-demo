package com.vtrip.booking.mapper;

import com.vtrip.booking.dto.request.CreateBookingRequestDto;
import com.vtrip.booking.dto.request.UpdateBookingRequestDto;
import com.vtrip.booking.dto.response.BookingResponseDto;
import com.vtrip.booking.entity.Booking;
import org.mapstruct.*;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface BookingMapper {

    BookingResponseDto toResponseDto(Booking entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    Booking toEntity(CreateBookingRequestDto requestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    void updateEntityFromDto(@MappingTarget Booking entity, UpdateBookingRequestDto updateDto);
}
