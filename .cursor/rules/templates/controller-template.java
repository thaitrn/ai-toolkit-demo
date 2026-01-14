package com.vtrip.{servicename}.controller;

import com.vtrip.{servicename}.dto.request.Create{Entity}RequestDto;import com.vtrip.{servicename}.dto.request.Update{Entity}RequestDto;import com.vtrip.{servicename}.dto.response.{Entity}ResponseDto;import com.vtrip.{servicename}.service.{Entity}Service;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/v1/{entities}")@Tag(name="{Entity}s",description="{Entity} management APIs")@RequiredArgsConstructor public class{Entity}Controller{

private final{Entity}Service service;

@Operation(summary="Get {entity} by ID")@ApiResponses({@ApiResponse(responseCode="200",description="{Entity} found"),@ApiResponse(responseCode="404",description="{Entity} not found")})@GetMapping("/{id}")public ResponseEntity<{Entity}ResponseDto>getById(@Parameter(description="{Entity} ID")@PathVariable Long id){return ResponseEntity.ok(service.getById(id));}

@Operation(summary="List all {entities} with pagination")@GetMapping public ResponseEntity<Page<{Entity}ResponseDto>>findAll(Pageable pageable){return ResponseEntity.ok(service.findAll(pageable));}

@Operation(summary="Create new {entity}")@ApiResponse(responseCode="201",description="{Entity} created successfully")@PostMapping public ResponseEntity<{Entity}ResponseDto>create(@Valid @RequestBody Create{Entity}RequestDto request){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));}

@Operation(summary="Update {entity}")@PutMapping("/{id}")public ResponseEntity<{Entity}ResponseDto>update(@PathVariable Long id,@Valid @RequestBody Update{Entity}RequestDto request){return ResponseEntity.ok(service.update(id,request));}

@Operation(summary="Delete {entity}")@ApiResponse(responseCode="204",description="{Entity} deleted successfully")@DeleteMapping("/{id}")public ResponseEntity<Void>delete(@PathVariable Long id){service.delete(id);return ResponseEntity.noContent().build();}}
