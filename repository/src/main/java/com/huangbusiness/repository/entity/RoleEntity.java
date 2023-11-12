package com.huangbusiness.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RoleEntity {

    private enum RoleName {
        Admin("Admin"),
        Customer("Customer");

        private final String value;

        RoleName(String value) {
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

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    @NotNull
    private RoleName name;
}
