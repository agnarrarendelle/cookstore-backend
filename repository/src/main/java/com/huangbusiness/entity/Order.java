package com.huangbusiness.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "order")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private enum OrderStatus {Paid, Unpaid, Cancelled, Finished}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull
    private OrderStatus status;

    @Column(name = "total_amount", precision = 7, scale = 2)
    @NotNull
    private BigDecimal totalAmount;

    @Column(name = "actual_amount", precision = 7, scale = 2)
    @NotNull
    private BigDecimal actualAmount;

    @Column(name = "remark", length = 512)
    private String remark;

    @Column(name = "customer_name", length = 128)
    private String customerName;

    @Column(name = "table_number")
    @NotNull
    private Short tableNumber;

    @Column(name = "cancelled_time")
    private Timestamp cancelledTime;

    @Column(name = "finished_time")
    private Timestamp finishedTime;

    @Column(name = "created_time")
    @CreationTimestamp
    private Timestamp createdTime;

    @Column(name = "updated_time")
    @UpdateTimestamp
    private Timestamp updatedTime;
}
