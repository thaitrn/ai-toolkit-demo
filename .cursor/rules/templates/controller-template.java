package com.vtrip.{servicename}.controller;

import com.vtrip.{servicename}.dto.request.Create{Entity}RequestDto;
import com.vtrip.{servicename}.dto.request.Update{Entity}RequestDto;
import com.vtrip.{servicename}.dto.response.{Entity}ResponseDto;
import com.vtrip.{servicename}.service.{Entity}Service;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/{entities}")
@Tag(name = "{Entity}s", description = "{Entity} management APIs")
@RequiredArgsConstructor
public class {Entity}Controller {

    private final {Entity}Service {entity}Service;

    @Operation(summary = "Get {entity} by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "{Entity} found"),
        @ApiResponse(responseCode = "404", description = "{Entity} not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<{Entity}ResponseDto> getById(
            @Parameter(description = "{Entity} ID") @PathVariable Long id) {
        return ResponseEntity.ok({entity}Service.getById(id));
    }

    @Operation(summary = "List all {entities} with pagination")
    @GetMapping
    public ResponseEntity<Page<{Entity}ResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.ok({entity}Service.findAll(pageable));
    }

    @Operation(summary = "Create new {entity}")
    @ApiResponse(responseCode = "201", description = "{Entity} created successfully")
    @PostMapping
    public ResponseEntity<{Entity}ResponseDto> create(
            @Valid @RequestBody Create{Entity}RequestDto request) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body({entity}Service.create(request));
    }

    @Operation(summary = "Update {entity}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "{Entity} updated"),
        @ApiResponse(responseCode = "404", description = "{Entity} not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<{Entity}ResponseDto> update(
            @Parameter(description = "{Entity} ID") @PathVariable Long id,
            @Valid @RequestBody Update{Entity}RequestDto request) {
        return ResponseEntity.ok({entity}Service.update(id, request));
    }

    @Operation(summary = "Delete {entity}")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "{Entity} deleted"),
        @ApiResponse(responseCode = "404", description = "{Entity} not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "{Entity} ID") @PathVariable Long id) {
        {entity}Service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
