package com.github.youssfbr.dio.dtos;

import com.github.youssfbr.dio.domain.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponseDTO {
    private String id;
    private String name;

    public CategoryResponseDTO(Category entity) {
        this.id = entity.getId().toString();
        this.name = entity.getName();
    }

}
