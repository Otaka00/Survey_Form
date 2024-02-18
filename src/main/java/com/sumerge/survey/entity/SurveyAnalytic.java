package com.sumerge.survey.entity;

import com.sumerge.survey.enumeration.SectionState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyAnalytic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;

    private Long duratingConfigId;

    @Enumerated(EnumType.STRING)
    private SectionState environmentalSection;

    @Enumerated(EnumType.STRING)
    private SectionState socialSection;

    @Enumerated(EnumType.STRING)
    private SectionState governmentalSection;

    private LocalDateTime createTimestamp;

    private LocalDateTime updateTimestamp;


}
