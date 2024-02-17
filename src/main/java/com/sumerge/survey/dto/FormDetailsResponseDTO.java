package com.sumerge.survey.dto;

import com.sumerge.survey.enumeration.SectionState;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormDetailsResponseDTO {

    private long id;
    private SectionState environmental_status;
    private SectionState social_status;
    private SectionState governmental_status;
    private LocalDateTime dateAndTime;

    private boolean completed;

}
