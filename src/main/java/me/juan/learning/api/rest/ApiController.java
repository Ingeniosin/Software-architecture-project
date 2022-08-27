package me.juan.learning.api.rest;

import com.google.gson.reflect.TypeToken;
import me.juan.learning.api.database.CustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

/*
    @RestController
    @RequestMapping("/api/**")
*/
public abstract class ApiController<T, TRepository extends JpaRepository<T, Integer> & CustomRepository<T>> {
    private final TRepository repository;

    public ApiController(TRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Response<T> Get(Request request) {
        return new Response<>(repository.findByRequest(request));
    }

    @PostMapping
    public T Post(@RequestParam String values) {
        T object = Utils.getGson().fromJson(values, new TypeToken<T>() {}.getType());
        return repository.save(object);
    }

    @PutMapping
    public T Put(T model) {
        return repository.save(model);
    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable int id) {
        repository.deleteById(id);
    }

}
