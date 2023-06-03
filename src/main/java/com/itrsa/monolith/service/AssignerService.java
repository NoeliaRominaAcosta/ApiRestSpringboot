package com.itrsa.monolith.service;

import com.itrsa.monolith.dto.AssignerDTO;

public interface AssignerService {
    AssignerDTO getAssigner(String dni);
    void editAssigner(AssignerDTO assigner);
    void deleteAssigner(String dni);
    void addAssigner(AssignerDTO assigner);
}
