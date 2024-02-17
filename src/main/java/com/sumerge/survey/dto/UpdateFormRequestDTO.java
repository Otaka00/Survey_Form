package com.sumerge.survey.dto;

import com.sumerge.survey.enumeration.SectionState;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class UpdateFormRequestDTO {
    private long formId;
    private Map<String, SectionState> sectionStates;
}
