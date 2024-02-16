package com.sumerge.survey.service;

// SurveyFormService.java
import com.sumerge.survey.enumeration.SectionState;
import com.sumerge.survey.entity.SurveyForm;
import com.sumerge.survey.repository.SurveyFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SurveyFormService {
    @Autowired
    private SurveyFormRepository surveyFormRepository;

    public List<SurveyForm> getUserSurveyForms(String userId) {
        return surveyFormRepository.findAllByUserId(userId);
    }

    public void recordSectionState(String userId, Map<String, SectionState> sectionStates) {
        Optional<SurveyForm> existingFormOptional = surveyFormRepository.findByUserId(userId);

        if (existingFormOptional.isPresent())
            updateForm(String.valueOf(existingFormOptional.get()), sectionStates);
         else createNewForm(userId, sectionStates);
    }

    public void createNewForm(String userId, Map<String, SectionState> sectionStates) {
        SurveyForm newForm = new SurveyForm();
        newForm.setUserId(userId);

        sectionStates.forEach((section, state) -> {
            setSectionState(newForm, section, state);
        });

        newForm.setLastUpdateTimestamp(LocalDateTime.now());
        surveyFormRepository.save(newForm);
    }

    public void updateForm(String userId, Map<String, SectionState> sectionStates) {
        Optional<SurveyForm> existingFormOptional = surveyFormRepository.findByUserId(userId);

        if (existingFormOptional.isPresent()) {
            SurveyForm existingForm = existingFormOptional.get();

            sectionStates.forEach((section, state) -> {
                setSectionState(existingForm, section, state);
            });

            existingForm.setLastUpdateTimestamp(LocalDateTime.now());
            surveyFormRepository.save(existingForm);
        }
    }

    private void setSectionState(SurveyForm form, String section, SectionState state) {
        switch (section) {
            case "environmental":
                form.setEnvironmentalSection(state);
                break;
            case "social":
                form.setSocialSection(state);
                break;
            case "governmental":
                form.setGovernmentalSection(state);
                break;
        }
    }
}
