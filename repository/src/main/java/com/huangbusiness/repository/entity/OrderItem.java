package com.huangbusiness.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "order_item")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number")
    @NotNull
    private Integer number;

    @Column(name = "total_amount", precision = 7, scale = 2)
    @NotNull
    private BigDecimal totalAmount;

    @Column(name = "actual_amount", precision = 7, scale = 2)
    @NotNull
    private BigDecimal actualAmount;

    @Column(name = "discount", precision = 3, scale = 2)
    private BigDecimal discount;

    @Column(name = "remark", length = 512)
    private String remark;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @NotNull
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull
    private Product product;

    @Column(name = "created_time")
    @CreationTimestamp
    private Timestamp createdTime;
}
