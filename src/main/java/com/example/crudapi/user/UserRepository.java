package com.example.crudapi.user;

import com.example.crudapi.common.InMemoryRepository;
import org.springframework.stereotype.Repository;

/**
 * User store. Empty body on purpose: it inherits all CRUD operations from
 * {@link InMemoryRepository}. Replacing this with a JPA repository later
 * would not change the service.
 */
@Repository
public class UserRepository extends InMemoryRepository<User> {
}
