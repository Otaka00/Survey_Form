package com.sumerge.survey.service;

import com.sumerge.survey.controller.SurveyFormController;
import com.sumerge.survey.enumeration.SectionState;
import com.sumerge.survey.entity.SurveyAnalytic;
import com.sumerge.survey.repository.SurveyFormRepository;
import com.sumerge.survey.dto.FormDetailsResponseDTO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
public class SurveyFormService {

    Logger logger = LoggerFactory.getLogger(SurveyFormService.class);

    @Autowired
    private SurveyFormRepository surveyFormRepository;

        public void configureStates(SurveyAnalytic surveyAnalytic) {
        if (surveyAnalytic.getEnvironmentalSection() == null)
            surveyAnalytic.setEnvironmentalSection(SectionState.OPENED_UNTOUCHED);

        if (surveyAnalytic.getSocialSection() == null)
            surveyAnalytic.setSocialSection(SectionState.UNOPENED);

        if (surveyAnalytic.getGovernmentalSection() == null)
            surveyAnalytic.setGovernmentalSection(SectionState.UNOPENED);

        if(surveyAnalytic.getGovernmentalSection() != SectionState.UNOPENED)
            surveyAnalytic.setSocialSection(SectionState.OPENED_UNTOUCHED);
    }

    public void createOrUpdate(Long companyId, Long duratingConfigId, Map<String, SectionState> sectionStates){
        List<SurveyAnalytic> existingFormList = surveyFormRepository.findByCompanyIdAndDuratingConfigId(companyId, duratingConfigId);
        logger.info("An INFO Message");

        if (!existingFormList.isEmpty()){
                SurveyAnalytic existingForm = existingFormList.get(0);

                sectionStates.forEach((section, state) ->
                        setSectionState(existingForm, section, state)
                );

                existingForm.setUpdateTimestamp(LocalDateTime.now());
                surveyFormRepository.save(existingForm);
            }

        else{
            SurveyAnalytic surveyAnalytic = new SurveyAnalytic();
            if (!sectionStates.isEmpty())
                sectionStates.forEach((section, state) -> {
                    setSectionState(surveyAnalytic, section, state);
                });

            configureStates(surveyAnalytic);
            surveyAnalytic.setCreateTimestamp(LocalDateTime.now());
            surveyAnalytic.setUpdateTimestamp(LocalDateTime.now());
            surveyAnalytic.setCompanyId(companyId);
            surveyAnalytic.setDuratingConfigId(duratingConfigId);
            surveyFormRepository.save(surveyAnalytic);
        }
    }

    private void setSectionState(SurveyAnalytic form, String section, SectionState state) {
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

    private boolean formSubmitted(SurveyAnalytic form) {
        return form.getEnvironmentalSection() == SectionState.COMPLETED &&
                form.getSocialSection() == SectionState.COMPLETED &&
                form.getGovernmentalSection() == SectionState.COMPLETED;
    }
    public FormDetailsResponseDTO getFormDetails(Long companyId, Long duratingConfigId){
        List<SurveyAnalytic> existingFormList = surveyFormRepository.findByCompanyIdAndDuratingConfigId(companyId, duratingConfigId);
        if(!existingFormList.isEmpty()){
            FormDetailsResponseDTO detailsResponse = new FormDetailsResponseDTO();
            detailsResponse.setId(existingFormList.get(0).getId());
            detailsResponse.setCompanyId(existingFormList.get(0).getCompanyId());
            detailsResponse.setDuratingConfigId(existingFormList.get(0).getDuratingConfigId());
            detailsResponse.setCreateTimestamp(existingFormList.get(0).getCreateTimestamp());
            detailsResponse.setUpdateTimestamp(existingFormList.get(0).getUpdateTimestamp());
            detailsResponse.setSocial_status(existingFormList.get(0).getSocialSection());
            detailsResponse.setGovernmental_status(existingFormList.get(0).getGovernmentalSection());
            detailsResponse.setEnvironmental_status(existingFormList.get(0).getEnvironmentalSection());
            detailsResponse.setCompleted(this.formSubmitted(existingFormList.get(0)));

            return detailsResponse;
        }
        return null;

    }

}
