package com.github.youssfbr.dio.domain.models;

import com.github.youssfbr.dio.dtos.ProductRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "text")
    private String description;
    private Double price;
    private String imgUrl;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant date;

    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Product(ProductRequestDTO productRequestDTO) {
        id = productRequestDTO.getId();
        name = productRequestDTO.getName();
        description = productRequestDTO.getDescription();
        price = productRequestDTO.getPrice();
        imgUrl = productRequestDTO.getImgUrl();
        date = productRequestDTO.getDate();
    }

}
