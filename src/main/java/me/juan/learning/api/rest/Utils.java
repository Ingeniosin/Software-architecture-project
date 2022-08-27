package me.juan.learning.api.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import lombok.Getter;

import java.util.List;

public class Utils {

    @Getter
    private static final Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();


    public static String toQuery(String tableName, Request request) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ''"+tableName+"''");
        if(request.getFilter().size() > 0) {
            queryBuilder.append(" WHERE ").append(toQuery(request.getFilter()));
        }
        if(request.getSort().size() > 0) {
            request.getSort().forEach(sortInfo -> {
                queryBuilder.append(" ORDER BY ").append("''").append(sortInfo.getSelector()).append("''").append(" ").append(sortInfo.isDesc() ? "desc" : "asc");
            });
        }
        if(request.getTake() > 0) {
            queryBuilder.append(" LIMIT ").append(request.getTake());
        }

        if(request.getSkip() > 0) {
            queryBuilder.append(" OFFSET ").append(request.getSkip());
        }
        String query = queryBuilder.toString().replaceAll("''", "\"");
        System.out.println("Running query: " + query);
        return query;
    }


    private static String toQuery(List<Object> filterArray) {
        StringBuilder filterBuilder = new StringBuilder();
        if(filterArray.get(0) instanceof String) {
            String paramCero = filterArray.get(0).toString();
            String paramUno = filterArray.get(1).toString();
            if(filterArray.size() == 3) {
                String paramDos = filterArray.get(2).toString();
                filterBuilder.append("''").append(paramCero).append("''").append(" ").append(paramUno).append(" ").append("'").append(paramDos).append("'");
            } else if(filterArray.size() == 2) {
                filterBuilder.append("''").append(paramCero).append("''").append(" ").append("'").append(paramUno).append("'");
            }
        } else {
            List<Object> objectsOne = (List<Object>) filterArray.get(0);
            List<Object> objectsTwo = (List<Object>) filterArray.get(2);
            filterBuilder.append("(").append(toQuery(objectsOne)).append(" ").append(filterArray.get(1)).append(" ").append(toQuery(objectsTwo)).append(")");
        }
        return filterBuilder.toString();
    }

}
