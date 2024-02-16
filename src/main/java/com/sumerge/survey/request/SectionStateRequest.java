package com.sumerge.survey.request;

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
public class SectionStateRequest {
    private long formId;
    private Map<String, SectionState> sectionStates;
}
