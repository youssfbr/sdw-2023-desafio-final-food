package com.github.youssfbr.dio.domain.repositories;

import com.github.youssfbr.dio.domain.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
