package com.example.crudapi.common;

import com.example.crudapi.exception.ResourceNotFoundException;

import java.util.List;

/**
 * Heart of the exercise: the CRUD logic lives here ONCE and is reused by
 * every concrete service through inheritance.
 * <p>
 * Three type parameters flow through the whole stack:
 * <ul>
 *   <li>{@code T}   – the entity (bounded to {@link BaseEntity} so we can
 *                     read/assign its id);</li>
 *   <li>{@code DTO} – what the API exchanges with the client;</li>
 *   <li>{@code ID}  – the identifier type.</li>
 * </ul>
 * A concrete service only supplies a repository, a mapper and a resource
 * name; it inherits create/read/update/delete for free.
 *
 * @param <T>   entity type
 * @param <DTO> data transfer object type
 * @param <ID>  identifier type
 */
public abstract class GenericService<T extends BaseEntity, DTO, ID> {

    protected final GenericRepository<T, ID> repository;
    protected final GenericMapper<T, DTO> mapper;

    protected GenericService(GenericRepository<T, ID> repository,
                             GenericMapper<T, DTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DTO create(DTO dto) {
        T entity = mapper.toEntity(dto);
        entity.setId(null); // ignore any client-sent id: the store assigns it
        T saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    public List<DTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public PageResponse<DTO> findAll(int page, int size) {
        if (page < 0) {
            page = 0;
        }
        if (size <= 0) {
            size = 20;
        }
        List<T> all = repository.findAll();
        long total = all.size();
        int from = Math.min(page * size, all.size());
        int to = Math.min(from + size, all.size());
        List<DTO> content = all.subList(from, to).stream()
                .map(mapper::toDto)
                .toList();
        int totalPages = (int) Math.ceil((double) total / size);
        return new PageResponse<>(content, page, size, total, totalPages);
    }

    public DTO findById(ID id) {
        T entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName(), id));
        return mapper.toDto(entity);
    }

    public DTO update(ID id, DTO dto) {
        T existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(resourceName(), id));
        T entity = mapper.toEntity(dto);
        entity.setId(existing.getId()); // keep the original id, don't create a new record
        return mapper.toDto(repository.save(entity));
    }

    public void delete(ID id) {
        boolean removed = repository.deleteById(id);
        if (!removed) {
            throw new ResourceNotFoundException(resourceName(), id);
        }
    }

    /** Human-readable name used in error messages (e.g. "User"). */
    protected abstract String resourceName();
}
