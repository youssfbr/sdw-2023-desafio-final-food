package com.github.youssfbr.dio.services;

import com.github.youssfbr.dio.domain.models.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category create(Category categoryToCreate);
}
