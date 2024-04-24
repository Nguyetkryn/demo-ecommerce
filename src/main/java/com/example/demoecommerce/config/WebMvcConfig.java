package com.example.demoecommerce.config;

import com.example.demoecommerce.bean.StringToDateConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public MultipartResolver multipartResolver() { return new StandardServletMultipartResolver(); }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry){
        formatterRegistry.addConverter(new StringToDateConverter());
    }

}
