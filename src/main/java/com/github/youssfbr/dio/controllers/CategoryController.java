package com.github.youssfbr.dio.controllers;

import com.github.youssfbr.dio.domain.models.Category;
import com.github.youssfbr.dio.dtos.CategoryDTO;
import com.github.youssfbr.dio.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> categoryList = categoryService.findAll();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        CategoryDTO categoryDTO = categoryService.findById(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category categoryToCreate) {
        Category categoryCreated = categoryService.create(categoryToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(categoryCreated);
    }

}
