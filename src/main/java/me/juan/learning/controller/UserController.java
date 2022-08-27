package me.juan.learning.controller;

import me.juan.learning.api.rest.ApiController;
import me.juan.learning.entity.User;
import me.juan.learning.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController extends ApiController<User, UserRepository> {

    public UserController(UserRepository repository) {
        super(repository);
    }
}
