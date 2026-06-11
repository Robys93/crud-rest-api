package com.example.crudapi.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory implementation of {@link GenericRepository}.
 * <p>
 * The id type is fixed to {@code Long} here because id generation
 * (an auto-increment) only makes sense for a concrete strategy, while the
 * {@link GenericRepository} interface stays fully generic.
 * <p>
 * Thread safety: {@link ConcurrentHashMap} guards the map and
 * {@link AtomicLong} the id sequence, so concurrent requests are safe
 * without explicit locks.
 *
 * @param <T> entity type, must expose an id via {@link BaseEntity}
 */
public abstract class InMemoryRepository<T extends BaseEntity>
        implements GenericRepository<T, Long> {

    private final Map<Long, T> store = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    @Override
    public T save(T entity) {
        if (entity.getId() == null) {
            entity.setId(sequence.incrementAndGet());
        }
        store.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }

    @Override
    public boolean existsById(Long id) {
        return store.containsKey(id);
    }
}
