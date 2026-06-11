package com.example.crudapi.common;

import java.util.List;
import java.util.Optional;

/**
 * Persistence contract shared by every entity.
 * <p>
 * Kept generic on {@code <T, ID>} so the rest of the application depends on
 * this abstraction, not on a concrete storage. Today the implementation is
 * in-memory; swapping it for JPA later would not touch the service layer.
 *
 * @param <T>  entity type
 * @param <ID> identifier type
 */
public interface GenericRepository<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    boolean deleteById(ID id);

    boolean existsById(ID id);
}
