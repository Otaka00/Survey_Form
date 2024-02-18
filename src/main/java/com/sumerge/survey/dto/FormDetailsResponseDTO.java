package com.sumerge.survey.dto;

import com.sumerge.survey.enumeration.SectionState;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormDetailsResponseDTO {

    private long id;
    private Long companyId;
    private Long duratingConfigId;

    private SectionState environmental_status;
    private SectionState social_status;
    private SectionState governmental_status;

    private LocalDateTime createTimestamp;

    private LocalDateTime updateTimestamp;
    private boolean completed;






}
