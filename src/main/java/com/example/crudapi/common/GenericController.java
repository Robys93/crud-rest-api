package com.example.crudapi.common;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Abstract REST controller exposing the five CRUD endpoints once for all
 * entities. A concrete controller only declares its {@code @RequestMapping}
 * base path and passes the right service to the constructor.
 * <p>
 * The endpoints are typed on the generic {@code DTO}/{@code ID}; Spring MVC
 * resolves the actual types (e.g. UserDto / Long) from the concrete subclass
 * at startup, so request bodies and path variables bind correctly.
 *
 * @param <T>   entity type
 * @param <DTO> data transfer object type
 * @param <ID>  identifier type
 */
public abstract class GenericController<T extends BaseEntity, DTO, ID> {

    protected final GenericService<T, DTO, ID> service;

    protected GenericController(GenericService<T, DTO, ID> service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DTO> create(@Valid @RequestBody DTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<PageResponse<DTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(service.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> findById(@PathVariable ID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTO> update(@PathVariable ID id, @Valid @RequestBody DTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
