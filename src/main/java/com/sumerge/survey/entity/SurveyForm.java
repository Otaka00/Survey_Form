package com.sumerge.survey.entity;

// SurveyForm.java
import com.sumerge.survey.enumeration.SectionState;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
public class SurveyForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;

    @Enumerated(EnumType.ORDINAL)
    private SectionState environmentalSection;

    @Enumerated(EnumType.ORDINAL)
    private SectionState socialSection;

    @Enumerated(EnumType.ORDINAL)
    private SectionState governmentalSection;

    private LocalDateTime lastUpdateTimestamp;
}
