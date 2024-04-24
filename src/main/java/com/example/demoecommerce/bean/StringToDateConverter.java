package com.example.demoecommerce.bean;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class StringToDateConverter implements Converter<String, Date> {
    private static final String DATA_FORMAT = "yyyy-MM-dd";

    //Chuyen doi tu chuoi sang ngay
    @Override
    public Date convert(String value){
        if (value.isBlank())
            return null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATA_FORMAT);
            return dateFormat.parse(value);
        } catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
