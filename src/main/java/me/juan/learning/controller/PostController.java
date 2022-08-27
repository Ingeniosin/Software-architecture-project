package me.juan.learning.controller;

import me.juan.learning.api.rest.ApiController;
import me.juan.learning.entity.Post;
import me.juan.learning.repository.PostRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController extends ApiController<Post, PostRepository> {

    public PostController(PostRepository repository) {
        super(repository);
    }
}

