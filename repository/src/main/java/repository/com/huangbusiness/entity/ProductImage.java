package repository.com.huangbusiness.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "product_image")
@Data
public class ProductImage {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 128)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "created_time")
    @NotNull
    private Timestamp createdTime;

    @Column(name = "updated_time")
    @NotNull
    private Timestamp updatedTime;
}