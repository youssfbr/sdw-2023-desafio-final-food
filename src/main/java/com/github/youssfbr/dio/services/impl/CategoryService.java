package com.github.youssfbr.dio.services.impl;

import com.github.youssfbr.dio.domain.models.Category;
import com.github.youssfbr.dio.domain.repositories.ICategoryRepository;
import com.github.youssfbr.dio.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    @Transactional
    public Category create(Category categoryToCreate) {
        if (categoryToCreate.getId() != null && categoryRepository.existsById(categoryToCreate.getId())) {
            throw new IllegalArgumentException("This Category ID already exists.");
        }

        if (categoryRepository.existsByName(categoryToCreate.getName())) {
            throw new IllegalArgumentException("This Category Name already exists.");
        }
        return categoryRepository.save(categoryToCreate);
    }
}
