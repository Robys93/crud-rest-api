package com.example.crudapi.common;

import java.util.List;

/**
 * Lightweight page wrapper for list endpoints (pagination bonus).
 * A record keeps it immutable and serializes cleanly to JSON.
 *
 * @param <T> element type (here always a DTO)
 */
public record PageResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages) {
}
