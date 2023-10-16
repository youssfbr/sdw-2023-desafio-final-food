package com.github.youssfbr.dio.services;

import com.github.youssfbr.dio.domain.models.Category;

public interface ICategoryService {
    Category findById(Long id);
    Category create(Category categoryToCreate);
}
