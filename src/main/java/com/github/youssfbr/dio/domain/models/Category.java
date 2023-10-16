package com.github.youssfbr.dio.domain.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    // TO DO List<Product> products;

}
