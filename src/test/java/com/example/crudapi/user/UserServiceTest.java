package com.example.crudapi.user;

import com.example.crudapi.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests UserService against the real in-memory repository and mapper.
 * No mocks needed: this also exercises the generic CRUD logic end to end.
 */
class UserServiceTest {

    private UserService service;

    @BeforeEach
    void setUp() {
        service = new UserService(new UserRepository(), new UserMapper());
    }

    @Test
    @DisplayName("create assigns a generated id and returns the DTO")
    void createAssignsId() {
        UserDto created = service.create(new UserDto(null, "Alice", "alice@example.com"));

        assertNotNull(created.getId());
        assertEquals("Alice", created.getName());
    }

    @Test
    @DisplayName("findById returns the previously created user")
    void findByIdReturnsUser() {
        UserDto created = service.create(new UserDto(null, "Bob", "bob@example.com"));

        UserDto found = service.findById(created.getId());

        assertEquals(created.getId(), found.getId());
        assertEquals("bob@example.com", found.getEmail());
    }

    @Test
    @DisplayName("findById throws when the user does not exist")
    void findByIdMissingThrows() {
        assertThrows(ResourceNotFoundException.class, () -> service.findById(999L));
    }

    @Test
    @DisplayName("update changes the fields but keeps the same id")
    void updateKeepsId() {
        UserDto created = service.create(new UserDto(null, "Carol", "carol@example.com"));

        UserDto updated = service.update(created.getId(),
                new UserDto(null, "Carol Smith", "carol.smith@example.com"));

        assertEquals(created.getId(), updated.getId());
        assertEquals("Carol Smith", updated.getName());
    }

    @Test
    @DisplayName("delete removes the user so a later read fails")
    void deleteRemovesUser() {
        UserDto created = service.create(new UserDto(null, "Dave", "dave@example.com"));

        service.delete(created.getId());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(created.getId()));
        assertTrue(service.findAll().isEmpty());
    }
}
