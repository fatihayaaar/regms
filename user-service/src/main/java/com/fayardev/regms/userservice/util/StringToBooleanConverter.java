package com.fayardev.regms.userservice.util;

public class StringToBooleanConverter implements org.springframework.core.convert.converter.Converter<String, Boolean> {

    @Override
    public Boolean convert(String source) {
        return Boolean.parseBoolean(source.toLowerCase());
    }

}