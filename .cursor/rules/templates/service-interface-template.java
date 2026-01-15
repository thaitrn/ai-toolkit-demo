package com.vtrip.{servicename}.service;

import com.vtrip.{servicename}.dto.request.Create{Entity}RequestDto;
import com.vtrip.{servicename}.dto.request.Update{Entity}RequestDto;
import com.vtrip.{servicename}.dto.response.{Entity}ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface {Entity}Service {

    {Entity}ResponseDto getById(Long id);

    Page<{Entity}ResponseDto> findAll(Pageable pageable);

    {Entity}ResponseDto create(Create{Entity}RequestDto request);

    {Entity}ResponseDto update(Long id, Update{Entity}RequestDto request);

    void delete(Long id);
}
