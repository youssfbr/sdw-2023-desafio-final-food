package com.github.youssfbr.dio.services;

import com.github.youssfbr.dio.dtos.ProductRequestDTO;
import com.github.youssfbr.dio.dtos.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Page<ProductResponseDTO> findAllPaged(Pageable pageable);
    List<ProductResponseDTO> findAll();
    ProductResponseDTO findById(Long id);
    ProductResponseDTO create(ProductRequestDTO productToCreate);
    ProductResponseDTO update(Long id, ProductRequestDTO productDTOToUpdate);
    void delete(Long id);
}
