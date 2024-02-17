package com.sumerge.survey.dto;

import com.sumerge.survey.enumeration.SectionState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SectionStateRequestDTO {
    private long formId;
    private Map<String, SectionState> sectionStates;
}
