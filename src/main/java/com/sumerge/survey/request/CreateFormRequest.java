package com.sumerge.survey.request;

import com.sumerge.survey.enumeration.SectionState;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class CreateFormRequest {
    private Map<String, SectionState> sectionStates;
}
