package com.github.youssfbr.dio.services.impl;

import com.github.youssfbr.dio.domain.models.Category;
import com.github.youssfbr.dio.domain.models.Product;
import com.github.youssfbr.dio.domain.repositories.ICategoryRepository;
import com.github.youssfbr.dio.domain.repositories.IProductRepository;
import com.github.youssfbr.dio.dtos.ProductRequestDTO;
import com.github.youssfbr.dio.dtos.ProductResponseDTO;
import com.github.youssfbr.dio.services.IProductService;
import com.github.youssfbr.dio.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final IProductRepository productRepository;
    private final ICategoryRepository categoryRepository;
    private static final String MESSAGE_ID_NOT_FOUND = "Resource not found with ID ";

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findAllPaged(Pageable pageable) {
        return productRepository
                .findAll(pageable)
                .map(ProductResponseDTO::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDTO> findAll() {

        return productRepository.findAll()
                .stream()
                .map(ProductResponseDTO::new)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDTO findById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID_NOT_FOUND + id));

        return new ProductResponseDTO(product, product.getCategories());
    }

    @Override
    @Transactional
    public ProductResponseDTO create(ProductRequestDTO productToCreate) {

        Product productToSave = new Product(productToCreate);
        productToSave.setId(null);
        Product productSaved = productRepository.save(productToSave);

        List<Category> categories = getCategories(productSaved);

        productSaved.getCategories().clear();
        productSaved.getCategories().addAll(categories);

        return new ProductResponseDTO(productSaved, productSaved.getCategories());
    }

    @Override
    @Transactional
    public ProductResponseDTO update(Long id, ProductRequestDTO productDTOToUpdate) {

        existsProduct(id);

        Product productToUpdate = new Product(productDTOToUpdate);
        productToUpdate.setId(id);
        Product productUpdated = productRepository.save(productToUpdate);

        getCategories(productUpdated);

        return new ProductResponseDTO(productUpdated, productUpdated.getCategories());
    }

    @Override
    public void delete(Long id) {
        existsProduct(id);
        productRepository.deleteById(id);
    }

    private Product existsProduct(Long id) {
        return productRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(MESSAGE_ID_NOT_FOUND + id));
    }

    private List<Category> getCategories(Product productSaved) {

        List<Category> categories = new ArrayList<>();

        productSaved.getCategories().forEach(x -> {
            Category category = categoryRepository.getReferenceById(x.getId());
            categories.add(category);
        });
        return categories;
    }

}
