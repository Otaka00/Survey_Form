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

    public void recordSectionState(String userId, String section, SectionState state) {
        Optional<SurveyForm> existingForm = surveyFormRepository.findByUserId(userId);

        if (existingForm.isPresent()) {
            updateSectionState(existingForm.get(), section, state);
        } else {
            createForm(userId, section, state);
        }
    }
    private void createForm(String userId, String section, SectionState state) {
        SurveyForm newForm = new SurveyForm();
        newForm.setUserId(userId);

        switch (section) {
            case "environmental":
                newForm.setEnvironmentalSection(state);
                break;
            case "social":
                newForm.setSocialSection(state);
                break;
            case "governmental":
                newForm.setGovernmentalSection(state);
                break;
        }

        newForm.setLastUpdateTimestamp(LocalDateTime.now());

        surveyFormRepository.save(newForm);
    }

    private void updateSectionState(SurveyForm form, String section, SectionState state) {

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

        form.setLastUpdateTimestamp(LocalDateTime.now());
        surveyFormRepository.save(form);
    }

    public void createNewForm(String userId, Map<String, SectionState> sectionStates) {
        SurveyForm newForm = new SurveyForm();
        newForm.setUserId(userId);

        // Set the section states based on the provided map
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

            // Update section states based on the provided map
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
            // Add cases for other sections if needed
        }
    }

}
