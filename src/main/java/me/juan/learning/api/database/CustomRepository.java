package me.juan.learning.api.database;

import me.juan.learning.api.rest.Request;

import java.util.List;

public interface CustomRepository<T> {

    List<T> findByRequest(Request request);

}
