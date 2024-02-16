package com.sumerge.survey.response;

import com.sumerge.survey.enumeration.SectionState;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormDetailsResponse {

    private long Id;
    private LocalDateTime dateAndTime;
    private SectionState environmental_status;
    private SectionState governmental_status;
    private SectionState social_status;

    private boolean completed;

}
