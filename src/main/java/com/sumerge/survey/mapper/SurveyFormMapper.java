package com.sumerge.survey.mapper;

import com.sumerge.survey.dto.FormDetailsResponseDTO;
import com.sumerge.survey.entity.SurveyForm;
import com.sumerge.survey.enumeration.SectionState;
import org.springframework.stereotype.Component;

@Component
public class SurveyFormMapper {

    public static FormDetailsResponseDTO mapToDto(SurveyForm surveyForm) {
        FormDetailsResponseDTO detailsResponseDTO = new FormDetailsResponseDTO();
        detailsResponseDTO.setId(surveyForm.getId());
        detailsResponseDTO.setDateAndTime(surveyForm.getLastSubmitTimestamp());
        detailsResponseDTO.setSocial_status(surveyForm.getSocialSection());
        detailsResponseDTO.setGovernmental_status(surveyForm.getGovernmentalSection());
        detailsResponseDTO.setEnvironmental_status(surveyForm.getEnvironmentalSection());
        detailsResponseDTO.setCompleted(formSubmitted(surveyForm));
        return detailsResponseDTO;
    }

    private static boolean formSubmitted(SurveyForm form) {
        return form.getEnvironmentalSection() == SectionState.COMPLETED &&
                form.getSocialSection() == SectionState.COMPLETED &&
                form.getGovernmentalSection() == SectionState.COMPLETED;
    }
}
