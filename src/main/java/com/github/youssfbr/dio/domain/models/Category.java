package com.github.youssfbr.dio.domain.models;

import com.github.youssfbr.dio.dtos.CategoryRequestDTO;
import com.github.youssfbr.dio.dtos.CategoryResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_category")
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(updatable = false, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedAt;

    public Category(CategoryResponseDTO categoryResponseDTO) {
        id = Long.parseLong(categoryResponseDTO.getId());
        name = categoryResponseDTO.getName();
    }

    public Category(CategoryRequestDTO categoryDTO) {
        BeanUtils.copyProperties(categoryDTO, this);
    }

    @PrePersist
    public void prePersist() {
        createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
    }

}
