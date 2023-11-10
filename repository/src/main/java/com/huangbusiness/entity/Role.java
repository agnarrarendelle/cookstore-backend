package com.huangbusiness.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private enum RoleName {Admin, Customer}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    @NotNull
    private RoleName name;
}
