package com.github.youssfbr.dio.domain.repositories;

import com.github.youssfbr.dio.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);
}
