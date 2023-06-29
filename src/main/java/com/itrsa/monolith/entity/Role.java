package com.itrsa.monolith.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String seniority;


    @ManyToOne
    @JoinColumn(name = "department_id")
    Department department;

    @ManyToMany(mappedBy = "roleList")
    List<Employee> employeeList;
}
