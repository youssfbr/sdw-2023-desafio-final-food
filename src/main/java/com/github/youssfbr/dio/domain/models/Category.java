package com.github.youssfbr.dio.domain.models;

import com.github.youssfbr.dio.dtos.CategoryRequestDTO;
import com.github.youssfbr.dio.dtos.CategoryResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    // TO DO List<Product> products;

    public Category(CategoryResponseDTO categoryResponseDTO) {
        this.id = Long.parseLong(categoryResponseDTO.getId());
        this.name = categoryResponseDTO.getName();
    }

    public Category(CategoryRequestDTO categoryDTO) {
        this.id = categoryDTO.getId();
        this.name = categoryDTO.getName();
    }

}
