package com.example.crudapi.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.Map;

/**
 * Uniform error payload returned for every handled exception.
 * Fields required by the spec: timestamp, status, message, path
 * (plus a human-readable {@code error} and optional per-field details).
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final OffsetDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    /** Populated only for validation errors: field name -> message. */
    private final Map<String, String> fieldErrors;
}
