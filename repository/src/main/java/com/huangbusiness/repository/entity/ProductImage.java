package com.huangbusiness.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "product_image")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductImage {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 128)
    private String name;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "created_time")
    @CreationTimestamp
    private Timestamp createdTime;

    @Column(name = "updated_time")
    @UpdateTimestamp
    private Timestamp updatedTime;
}