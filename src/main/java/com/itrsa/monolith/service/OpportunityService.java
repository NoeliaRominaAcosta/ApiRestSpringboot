package com.itrsa.monolith.service;

import com.itrsa.monolith.dto.OpportunityDTO;
import java.util.List;

public interface OpportunityService {

    OpportunityDTO getOpportunity(String code);
    List<OpportunityDTO> listOpportunities();
    void editOpportunity(OpportunityDTO opportunity);
    void createOpportunity(OpportunityDTO opportunity , Iterable<String> skillsCode);
    void deleteOpportunity(String code);
}
