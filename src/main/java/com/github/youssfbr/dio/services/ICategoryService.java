package com.github.youssfbr.dio.services;

import com.github.youssfbr.dio.dtos.CategoryRequestDTO;
import com.github.youssfbr.dio.dtos.CategoryResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    Page<CategoryResponseDTO> findAllPaged(Pageable pageable);
    List<CategoryResponseDTO> findAll();
    CategoryResponseDTO findById(Long id);
    CategoryResponseDTO create(CategoryRequestDTO categoryToCreate);
    CategoryResponseDTO update(Long id, CategoryRequestDTO categoryDTOToUpdate);
    void delete(Long id);
}
