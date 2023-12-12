package com.fayardev.regms.userservice.util;

public class BooleanToStringConverter implements org.springframework.core.convert.converter.Converter<Boolean, String> {

    @Override
    public String convert(Boolean source) {
        return source.toString().toUpperCase();
    }

}