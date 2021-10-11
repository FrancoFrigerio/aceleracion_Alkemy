package com.alkemy.java.controller;

import com.alkemy.java.service.INewsService;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mariela
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    INewsService newsService;
    
    @Autowired
    MessageSource messageSource;
    
    @Value("success.deleted")
    String messageDeleted;
    
    
    @DeleteMapping("/:{id}")
    public ResponseEntity<?> deleteNews(@PathVariable("id") Long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>(messageSource.getMessage(messageDeleted, null, Locale.getDefault()), HttpStatus.OK);
    }
}