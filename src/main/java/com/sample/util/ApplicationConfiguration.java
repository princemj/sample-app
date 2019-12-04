package com.sample.util;

import java.util.Objects;

import static java.lang.String.format;

public abstract class ApplicationConfiguration {
    public abstract Object getValue(String name);

    public String getValueAsString(String name) {
        Object value = getValue(name);
        if (value == null) {
            return "";
        }
        else return value.toString();
    }

    public Integer getValueAsInt(String name) {
        String value = this.getValueAsString(name);
        return Objects.equals(value, "") ? 0 : Integer.valueOf(value);
    }

    public Integer getValueAsInt(String name, int defaultValue) {
        Object value = getValue(name);
        return value != null? getValueAsInt(name) : defaultValue;
    }

}
