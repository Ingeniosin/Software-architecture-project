package me.juan.learning.api.rest;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Request {

    private int skip;
    private int take;

    private List<SortInfo> sort = new ArrayList<>();

    private List<Object> filter = new ArrayList<>();


}