package repository.com.huangbusiness.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 128, unique = true)
    private String name;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    @NotNull
    private Timestamp updatedTime;
}
