package com.vtrip.payment.mapper;

import com.vtrip.payment.dto.request.CreatePaymentRequestDto;
import com.vtrip.payment.dto.request.UpdatePaymentRequestDto;
import com.vtrip.payment.dto.response.PaymentResponseDto;
import com.vtrip.payment.entity.Payment;
import org.mapstruct.*;

import java.util.List;

/**
 * ✅ WITH AI TOOLKIT - All 5 developers write EXACTLY this code
 * 
 * Enforced by:
 * - 04-mapstruct-mapper.mdc: MapStruct patterns
 * 
 * Benefits:
 * - ✅ Compile-time type safety
 * - ✅ Never miss a field (build fails if mapping incomplete)
 * - ✅ Consistent null handling
 * - ✅ Easy to maintain when entity changes
 * - ✅ No manual mapping code
 * - ✅ Spring integration with componentModel = "spring"
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PaymentMapper {

    /**
     * Entity -> Response DTO
     */
    PaymentResponseDto toResponseDto(Payment entity);

    /**
     * Request DTO -> Entity (for create)
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    Payment toEntity(CreatePaymentRequestDto requestDto);

    /**
     * Update entity from DTO (for PATCH/PUT operations)
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    void updateEntityFromDto(@MappingTarget Payment entity, UpdatePaymentRequestDto updateDto);

    /**
     * List mappings
     */
    List<PaymentResponseDto> toResponseDtoList(List<Payment> entities);
}
