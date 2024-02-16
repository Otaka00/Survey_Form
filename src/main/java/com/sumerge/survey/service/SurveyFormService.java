package com.sumerge.survey.service;

// SurveyFormService.java
import com.sumerge.survey.enumeration.SectionState;
import com.sumerge.survey.entity.SurveyForm;
import com.sumerge.survey.repository.SurveyFormRepository;
import com.sumerge.survey.response.FormDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class SurveyFormService {

    @Autowired
    private SurveyFormRepository surveyFormRepository;


    public void createNewForm( Map<String, SectionState> sectionStates) {
        try {
            SurveyForm newForm = new SurveyForm();
            if (!sectionStates.isEmpty())
                sectionStates.forEach((section, state) -> {
                    setSectionState(newForm, section, state);
                });

            newForm.setLastSubmitTimestamp(LocalDateTime.now());
            surveyFormRepository.save(newForm);
        }catch (Exception ex){
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
            Optional<SurveyForm> formOptional = surveyFormRepository.findById(formId);
            if(formOptional.isPresent()) {
                SurveyForm form = formOptional.get();
                return form.getLastSubmitTimestamp();
            }
        return null;
    }

    public FormDetailsResponse getFormDetails(long formId){
        Optional<SurveyForm> formOptional = surveyFormRepository.findById(formId);
        if(formOptional.isPresent()){
            FormDetailsResponse detailsResponse = new FormDetailsResponse();
            detailsResponse.setId(formOptional.get().getId());
            detailsResponse.setDateAndTime(formOptional.get().getLastSubmitTimestamp());
            detailsResponse.setSocial_status(formOptional.get().getSocialSection());
            detailsResponse.setGovernmental_status(formOptional.get().getGovernmentalSection());
            detailsResponse.setEnvironmental_status(formOptional.get().getEnvironmentalSection());
            detailsResponse.setCompleted(this.formSubmitted(formOptional.get()));

            return detailsResponse;
        }
        return null;

    }
}
