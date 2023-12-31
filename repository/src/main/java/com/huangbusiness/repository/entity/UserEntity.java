package com.huangbusiness.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 128)
    @NotNull
    private String username;

    @Column(name = "email", length = 256)
    @NotNull
    private String email;

    @Column(name = "password", length = 64)
    @NotNull
    private String password;

    @Column(name = "created_time")
    @CreationTimestamp
    private Timestamp createdTime;

    @Column(name = "updated_time")
    @UpdateTimestamp
    private Timestamp updatedTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
               joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<RoleEntity> roles;
}
