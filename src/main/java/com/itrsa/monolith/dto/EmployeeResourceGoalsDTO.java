package com.itrsa.monolith.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResourceGoalsDTO {
   private String longGoal;
   private String shortGoal;
   private Iterable<ResourceDTO> resources;
}
