package com.github.youssfbr.dio.dtos;

import com.github.youssfbr.dio.domain.models.Category;
import com.github.youssfbr.dio.domain.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Instant date;
    private List<CategoryResponseDTO> categories = new ArrayList<>();

    public ProductResponseDTO(Product entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
        date = entity.getDate();
    }

    public ProductResponseDTO(Product entity, Set<Category> categories) {
        this(entity);
        categories.forEach(cat -> this.categories.add(new CategoryResponseDTO(cat)));
    }

}
