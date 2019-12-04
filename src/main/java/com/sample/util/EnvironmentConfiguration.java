package com.sample.util;

public class EnvironmentConfiguration extends ApplicationConfiguration {
    @Override
    public Object getValue(String name) {
        return System.getenv(name);
    }
}
