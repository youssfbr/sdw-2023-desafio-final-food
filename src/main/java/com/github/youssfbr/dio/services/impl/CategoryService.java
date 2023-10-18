package com.github.youssfbr.dio.services.impl;

import com.github.youssfbr.dio.domain.models.Category;
import com.github.youssfbr.dio.domain.repositories.ICategoryRepository;
import com.github.youssfbr.dio.dtos.CategoryRequestDTO;
import com.github.youssfbr.dio.dtos.CategoryResponseDTO;
import com.github.youssfbr.dio.services.ICategoryService;
import com.github.youssfbr.dio.services.exceptions.DatabaseException;
import com.github.youssfbr.dio.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<CategoryResponseDTO> findAllPaged(Pageable pageable) {
        return categoryRepository
                .findAll(pageable)
                .map(CategoryResponseDTO::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> findAll() {

        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponseDTO::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO findById(Long id) {

        return categoryRepository.findById(id)
                .map(CategoryResponseDTO::new)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID_NOT_FOUND + id));
    }

    @Override
    @Transactional
    public CategoryResponseDTO create(CategoryRequestDTO categoryToCreate) {

        if (categoryRepository.existsByName(categoryToCreate.getName())) {
            throw new IllegalArgumentException("This Category Name already exists.");
        }

        Category categoryToSave = new Category(categoryToCreate);
        categoryToSave.setId(null);
        Category categorySaved = categoryRepository.save(categoryToSave);

        return new CategoryResponseDTO(categorySaved);
    }

    @Override
    @Transactional
    public CategoryResponseDTO update(Long id, CategoryRequestDTO categoryDTOToUpdate) {

        existsCategory(id);

        if (categoryRepository.existsByName(categoryDTOToUpdate.getName())) {
            throw new IllegalArgumentException("This Category Name already exists.");
        }

        Category categoryToUpdate = new Category(categoryDTOToUpdate);
        categoryToUpdate.setId(id);
        Category categoryUpdated = categoryRepository.save(categoryToUpdate);

        return new CategoryResponseDTO(categoryUpdated);
    }

    @Override
    public void delete(Long id) {
        try {
            existsCategory(id);
            categoryRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation.");
        }
    }

    private Category existsCategory(Long id) {
        return categoryRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID_NOT_FOUND + id));
    }

}
