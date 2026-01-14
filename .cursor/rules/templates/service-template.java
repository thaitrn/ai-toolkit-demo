package com.vtrip.{servicename}.service.impl;

import com.vtrip.{servicename}.dto.request.Create{Entity}RequestDto;
import com.vtrip.{servicename}.dto.request.Update{Entity}RequestDto;
import com.vtrip.{servicename}.dto.response.{Entity}ResponseDto;
import com.vtrip.{servicename}.entity.{Entity};
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

    private final {Entity}Repository repository;
    private final {Entity}Mapper mapper;

    @Override
    @Transactional(readOnly = true)
    public {Entity}ResponseDto getById(Long id) {
        return repository.findById(id)
            .map(mapper::toResponseDto)
            .orElseThrow(() -> NotFoundException.forEntity("{Entity}", id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<{Entity}ResponseDto> findAll(Pageable pageable) {
        return repository.findAll(pageable)
            .map(mapper::toResponseDto);
    }

    @Override
    @Transactional
    public {Entity}ResponseDto create(Create{Entity}RequestDto request) {
        log.info("Creating new {entity}: {}", request);
        
        var entity = mapper.toEntity(request);
        var saved = repository.save(entity);
        
        log.info("Created {entity} with id: {}", saved.getId());
        return mapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public {Entity}ResponseDto update(Long id, Update{Entity}RequestDto request) {
        log.info("Updating {entity} with id: {}", id);
        
        var entity = repository.findById(id)
            .orElseThrow(() -> NotFoundException.forEntity("{Entity}", id));
            
        mapper.updateEntityFromDto(entity, request);
        var saved = repository.save(entity);
        
        return mapper.toResponseDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.info("Deleting {entity} with id: {}", id);
        
        if (!repository.existsById(id)) {
            throw NotFoundException.forEntity("{Entity}", id);
        }
        
        repository.deleteById(id);
    }
}
