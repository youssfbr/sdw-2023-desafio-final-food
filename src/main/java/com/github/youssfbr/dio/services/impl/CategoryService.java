package com.github.youssfbr.dio.services.impl;

import com.github.youssfbr.dio.domain.models.Category;
import com.github.youssfbr.dio.domain.repositories.ICategoryRepository;
import com.github.youssfbr.dio.dtos.CategoryDTO;
import com.github.youssfbr.dio.services.ICategoryService;
import com.github.youssfbr.dio.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private static final String MESSAGE_ID_NOT_FOUND = "Resource not found with ID ";

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {

        return categoryRepository.findAll()
                .stream()
                .map(CategoryDTO::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {

        return categoryRepository.findById(id)
                .map(CategoryDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID_NOT_FOUND + id));
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
