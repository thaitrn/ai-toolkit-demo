package com.vtrip.{servicename}.mapper;

import com.vtrip.{servicename}.dto.request.Create{Entity}RequestDto;
import com.vtrip.{servicename}.dto.request.Update{Entity}RequestDto;
import com.vtrip.{servicename}.dto.response.{Entity}ResponseDto;
import com.vtrip.{servicename}.entity.{Entity};
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface {Entity}Mapper {

    {Entity}ResponseDto toResponseDto({Entity} entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    {Entity} toEntity(Create{Entity}RequestDto requestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "customerId", ignore = true)
    void updateEntityFromDto(@MappingTarget {Entity} entity, Update{Entity}RequestDto updateDto);
}
