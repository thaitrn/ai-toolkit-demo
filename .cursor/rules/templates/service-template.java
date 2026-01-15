package com.vtrip.{servicename}.service.impl;

import com.vtrip.{servicename}.dto.request.Create{Entity}RequestDto;
import com.vtrip.{servicename}.dto.request.Update{Entity}RequestDto;
import com.vtrip.{servicename}.dto.response.{Entity}ResponseDto;
import com.vtrip.{servicename}.exception.NotFoundException;
import com.vtrip.{servicename}.mapper.{Entity}Mapper;
import com.vtrip.{servicename}.repository.{Entity}Repository;
import com.vtrip.{servicename}.service.{Entity}Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class {Entity}ServiceImpl implements {Entity}Service {

    private final {Entity}Repository {entity}Repository;
    private final {Entity}Mapper {entity}Mapper;

    @Override
    @Transactional(readOnly = true)
    public {Entity}ResponseDto getById(Long id) {
        return {entity}Repository.findById(id)
            .map({entity}Mapper::toResponseDto)
            .orElseThrow(() -> NotFoundException.forEntity("{Entity}", id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<{Entity}ResponseDto> findAll(Pageable pageable) {
        return {entity}Repository.findAll(pageable)
            .map({entity}Mapper::toResponseDto);
    }

    @Override
    @Transactional
    public {Entity}ResponseDto create(Create{Entity}RequestDto request) {
        log.debug("Creating {entity}: {}", request);
        var entity = {entity}Mapper.toEntity(request);
        var saved = {entity}Repository.save(entity);
        log.debug("Created {entity} with id: {}", saved.getId());
        return {entity}Mapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public {Entity}ResponseDto update(Long id, Update{Entity}RequestDto request) {
        log.debug("Updating {entity} with id: {}", id);
        var entity = {entity}Repository.findById(id)
            .orElseThrow(() -> NotFoundException.forEntity("{Entity}", id));
        {entity}Mapper.updateEntityFromDto(entity, request);
        var saved = {entity}Repository.save(entity);
        log.debug("Updated {entity} with id: {}", saved.getId());
        return {entity}Mapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Deleting {entity} with id: {}", id);
        if (!{entity}Repository.existsById(id)) {
            throw NotFoundException.forEntity("{Entity}", id);
        }
        {entity}Repository.deleteById(id);
        log.debug("Deleted {entity} with id: {}", id);
    }
}
