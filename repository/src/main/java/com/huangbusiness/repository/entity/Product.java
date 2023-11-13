package com.huangbusiness.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    public static enum ProductStatus {
        Available("Available"),
        Unavailable("Unavailable");
        private final String value;

        ProductStatus(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 128)
    @NotNull
    private String name;

    @Column(name = "status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "sold_number")
    @NotNull
    private Integer soldNumber;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "price", precision = 6, scale = 2)
    @NotNull
    private BigDecimal price;

    @Column(name = "discount", precision = 3, scale = 2)
    private BigDecimal discount;

    @Column(name = "created_time")
    @CreationTimestamp
    private Timestamp createdTime;

    @Column(name = "updated_time")
    @UpdateTimestamp
    private Timestamp updatedTime;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "category_id", insertable = false, updatable = false)
    private Integer categoryId;
}
