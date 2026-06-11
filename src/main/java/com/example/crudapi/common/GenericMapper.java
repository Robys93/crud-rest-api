package com.example.crudapi.common;

/**
 * Converts between an entity and its DTO.
 * <p>
 * The generic service knows nothing about the concrete fields of User or
 * Product: it just delegates the conversion to the mapper it receives.
 * This is what keeps the CRUD logic reusable.
 *
 * @param <T>   entity type
 * @param <DTO> data transfer object type
 */
public interface GenericMapper<T, DTO> {

    DTO toDto(T entity);

    T toEntity(DTO dto);
}
