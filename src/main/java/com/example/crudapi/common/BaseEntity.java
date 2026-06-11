package com.example.crudapi.common;

import lombok.Getter;
import lombok.Setter;

/**
 * Base class for every domain entity.
 * <p>
 * It holds the only field that ALL entities share: the identifier.
 * Bounding the generic layer to {@code T extends BaseEntity} lets the
 * generic service read and assign the id without knowing the concrete type.
 */
@Getter
@Setter
public abstract class BaseEntity {

    /** Server-generated identifier. Null means "not persisted yet". */
    private Long id;
}
