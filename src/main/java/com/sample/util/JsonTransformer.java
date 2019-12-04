package com.sample.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
    private Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd hh:mm:ss.S")
            .create();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}
