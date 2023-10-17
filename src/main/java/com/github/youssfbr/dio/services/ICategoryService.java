package com.github.youssfbr.dio.services;

import com.github.youssfbr.dio.domain.models.Category;
import com.github.youssfbr.dio.dtos.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryDTO> findAll();
    Category findById(Long id);
    Category create(Category categoryToCreate);
}
