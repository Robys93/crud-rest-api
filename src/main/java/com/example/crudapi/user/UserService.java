package com.example.crudapi.user;

import com.example.crudapi.common.GenericService;
import org.springframework.stereotype.Service;

/**
 * User service. All CRUD logic is inherited from {@link GenericService};
 * here we only declare the concrete types and the resource name used in
 * error messages. This is where adding User-specific business rules would go.
 */
@Service
public class UserService extends GenericService<User, UserDto, Long> {

    public UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }

    @Override
    protected String resourceName() {
        return "User";
    }
}
