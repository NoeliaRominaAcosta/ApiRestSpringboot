package com.itrsa.monolith.requestobjects;

import com.itrsa.monolith.dto.OpportunityDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpportunitySkillReq {
    OpportunityDTO opportunity;
    Iterable<String> skillCodes;
}
