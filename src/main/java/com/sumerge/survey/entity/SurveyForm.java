package com.sumerge.survey.entity;

import com.sumerge.survey.enumeration.SectionState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SectionState environmentalSection;

    @Enumerated(EnumType.STRING)
    private SectionState socialSection;

    @Enumerated(EnumType.STRING)
    private SectionState governmentalSection;

    private LocalDateTime lastSubmitTimestamp;

    @PrePersist
    public void prePersist() {
        if (environmentalSection == null) {
            environmentalSection = SectionState.OPENED_UNTOUCHED;
        }
        if (socialSection == null) {
            socialSection = SectionState.UNOPENED;
        }
        if (governmentalSection == null) {
            governmentalSection = SectionState.UNOPENED;
        }
    }
}
