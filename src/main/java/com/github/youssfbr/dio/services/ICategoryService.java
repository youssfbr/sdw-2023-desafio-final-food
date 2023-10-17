package com.github.youssfbr.dio.services;

import com.github.youssfbr.dio.dtos.CategoryRequestDTO;
import com.github.youssfbr.dio.dtos.CategoryResponseDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponseDTO> findAll();
    CategoryResponseDTO findById(Long id);
    CategoryResponseDTO create(CategoryRequestDTO categoryToCreate);
    CategoryResponseDTO update(Long id, CategoryRequestDTO categoryDTOToUpdate);
    void delete(Long id);
}
