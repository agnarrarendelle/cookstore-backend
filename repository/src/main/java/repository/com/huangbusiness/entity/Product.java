package repository.com.huangbusiness.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "product")
@Data
public class Product {
    private enum ProductStatus {Available, Unavailable}

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
    @NotNull
    private Timestamp createdTime;

    @Column(name = "updated_time")
    @NotNull
    private Timestamp updatedTime;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
