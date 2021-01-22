package org.example.web.config;

import org.example.app.services.BookService;
import org.example.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.util.HashMap;

@Configuration
public class WebConfig {

    @Bean
    public HashMap<String, Object> allModelFieldsAttributeBookShelf() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("book", new Book());
        map.put("bookIdToRemoveDTO", new BookIdToRemoveDTO());
        map.put("bookAuthorToRemoveDTO", new BookAuthorToRemoveDTO());
        map.put("bookTitleToRemoveDTO", new BookTitleToRemoveDTO());
        map.put("bookSizeToRemoveDTO", new BookSizeToRemoveDTO());
        return new HashMap<>(map);
    }

    @Bean
    public HashMap<String, Object> allModelFieldsAttributeFilter() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("filterDTO", new FilterDTO());
        return new HashMap<>(map);
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(-1);
        return multipartResolver;
    }
}
