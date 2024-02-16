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

    public void recordSectionState(long formId, Map<String, SectionState> sectionStates) {
        Optional<SurveyForm> existingFormOptional = surveyFormRepository.findByFormId(formId);

        if (existingFormOptional.isPresent())
            updateForm(existingFormOptional.get().getId(), sectionStates);
         else createNewForm(formId, sectionStates);
    }

    public void createNewForm(long formId, Map<String, SectionState> sectionStates) {
        SurveyForm newForm = new SurveyForm();
        newForm.setId(formId);

        sectionStates.forEach((section, state) -> {
            setSectionState(newForm, section, state);
        });

        newForm.setLastSubmitTimestamp(LocalDateTime.now());
        surveyFormRepository.save(newForm);
    }

    public void updateForm(long formId, Map<String, SectionState> sectionStates) {
        Optional<SurveyForm> existingFormOptional = surveyFormRepository.findByFormId(formId);

        if (existingFormOptional.isPresent()) {
            SurveyForm existingForm = existingFormOptional.get();

            sectionStates.forEach((section, state) -> {
                setSectionState(existingForm, section, state);
            });

            existingForm.setLastSubmitTimestamp(LocalDateTime.now());
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
    private boolean formSubmitted(SurveyForm form) {
        return form.getEnvironmentalSection() == SectionState.COMPLETED &&
                form.getSocialSection() == SectionState.COMPLETED &&
                form.getGovernmentalSection() == SectionState.COMPLETED;
    }

    public LocalDateTime getLastSubmitTimestamp(long formId) {
        Optional<SurveyForm> formOptional = surveyFormRepository.findByFormId(formId);
            SurveyForm form = formOptional.get();
            return form.getLastSubmitTimestamp();
    }
}
