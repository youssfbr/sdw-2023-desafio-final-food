package com.github.youssfbr.dio.dtos;

import com.github.youssfbr.dio.domain.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {
    private Long id;
    private String name;

    public CategoryRequestDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
