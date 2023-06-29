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
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    private String dni;

    private String role;

    private String longGoal;

    private String shortGoal;

    @OneToMany(mappedBy = "coach")
    List<Employee> employees;

}