package com.sumerge.survey.dto;

import com.sumerge.survey.enumeration.SectionState;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SectionStateRequest {
    private String userId;
    private String section;
    private SectionState state;
}
