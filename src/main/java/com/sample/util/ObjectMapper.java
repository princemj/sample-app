package com.sample.util;

import com.google.gson.Gson;

public class ObjectMapper<T> {

    public T map(String jsonString, Class<T> object) {
        return new Gson().fromJson(jsonString, object);
    }
}
