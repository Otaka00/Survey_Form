package com.sumerge.survey.service;

import com.sumerge.survey.enumeration.SectionState;
import com.sumerge.survey.entity.SurveyForm;
import com.sumerge.survey.exception.FormNotFoundException;
import com.sumerge.survey.mapper.SurveyFormMapper;
import com.sumerge.survey.repository.SurveyFormRepository;
import com.sumerge.survey.dto.FormDetailsResponseDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class SurveyFormService {

    @Autowired
    private SurveyFormRepository surveyFormRepository;

    @Autowired
    private SurveyFormMapper surveyFormMapper;

    @PersistenceContext
    private EntityManager entityManager;


    public void createNewForm( Map<String, SectionState> sectionStates) {
        try {
            SurveyForm newForm = new SurveyForm();
            if (!sectionStates.isEmpty())
                sectionStates.forEach((section, state) -> {
                    setSectionState(newForm, section, state);
                });

            newForm.setLastSubmitTimestamp(LocalDateTime.now());
            surveyFormRepository.save(newForm);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void updateForm(long formId, Map<String, SectionState> sectionStates) {
        Optional<SurveyForm> existingFormOptional = surveyFormRepository.findById(formId);

        if (existingFormOptional.isPresent()) {
            SurveyForm existingForm = existingFormOptional.get();

            sectionStates.forEach((section, state) ->
                setSectionState(existingForm, section, state)
            );

            existingForm.setLastSubmitTimestamp(LocalDateTime.now());
            surveyFormRepository.save(existingForm);
            System.out.println("Form details: " + existingForm);
        }
        else {
            throw new FormNotFoundException("Form not found with ID: " + formId);
        }
    }

    @Transactional
    private void update(SurveyForm surveyForm){
        entityManager.merge(surveyForm);
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
            Optional<SurveyForm> formOptional = surveyFormRepository.findById(formId);
            if(formOptional.isPresent()) {
                SurveyForm form = formOptional.get();
                return form.getLastSubmitTimestamp();
            }
        return null;
    }

    public FormDetailsResponseDTO getFormDetails(long formId) {
        return surveyFormRepository.findById(formId)
                .map(form -> {
                    FormDetailsResponseDTO detailsResponse = new FormDetailsResponseDTO();
                    detailsResponse.setId(form.getId());
                    detailsResponse.setDateAndTime(form.getLastSubmitTimestamp());
                    detailsResponse.setSocial_status(form.getSocialSection());
                    detailsResponse.setGovernmental_status(form.getGovernmentalSection());
                    detailsResponse.setEnvironmental_status(form.getEnvironmentalSection());
                    detailsResponse.setCompleted(this.formSubmitted(form));
                    return detailsResponse;
                })
                .orElse(null);
    }
}
