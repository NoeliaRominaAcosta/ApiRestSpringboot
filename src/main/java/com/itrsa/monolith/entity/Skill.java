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
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skillCode;
    private String name;
    private String seniority;

    @ManyToMany(mappedBy = "skillList")
    List<Employee> employeeList;

    @ManyToMany(mappedBy = "skillListOpportunity")
    List<Opportunity> opportunityList;
}
