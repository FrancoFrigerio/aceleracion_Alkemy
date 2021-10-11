
package com.alkemy.java.controller;

import com.alkemy.java.dto.CategoryListRespDto;
import com.alkemy.java.dto.CategoryRequestDto;
import com.alkemy.java.exception.InvalidDataException;
import com.alkemy.java.service.ICategoryService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Mariela
 */
@RestController
@RequestMapping("/categories")
public class CategoryController {
    
    @Autowired
    ICategoryService iCategoryService;
    
    @PostMapping
    public ResponseEntity <?> createCategory (@Valid @RequestBody (required = true) CategoryRequestDto categoryRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            throw new InvalidDataException(bindingResult);
        
            return new ResponseEntity<>(iCategoryService.createCategory(categoryRequest), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryListRespDto>> getAllCategories(){
        List<CategoryListRespDto> categories  = iCategoryService.findAllCategories()
                .stream()
                .map(CategoryListRespDto::new)
                .collect(Collectors.toList());
        return categories.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(categories) : ResponseEntity.ok(categories);
    }
}