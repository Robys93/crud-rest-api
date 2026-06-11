package com.example.crudapi.user;

import com.example.crudapi.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * User entity. Extends {@link BaseEntity} to inherit the id, so it fits the
 * generic CRUD layer with no extra wiring.
 */
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
