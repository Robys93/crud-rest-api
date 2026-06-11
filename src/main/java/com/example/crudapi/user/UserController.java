package com.example.crudapi.user;

import com.example.crudapi.common.GenericController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes /users. The five CRUD endpoints come from
 * {@link GenericController}; this class only fixes the base path and the
 * concrete types (User, UserDto, Long).
 */
@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "CRUD operations on users")
public class UserController extends GenericController<User, UserDto, Long> {

    public UserController(UserService service) {
        super(service);
    }
}
