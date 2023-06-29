package com.itrsa.monolith.dto;


import lombok.Data;

@Data
public class EmployeeResourceGoalsDTO {
   private String longGoal;

   private String shortGoal;

   private Iterable<ResourceDTO> resources;
}
