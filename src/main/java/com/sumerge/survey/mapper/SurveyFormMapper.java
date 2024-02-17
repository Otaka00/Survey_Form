package com.sumerge.survey.mapper;

import com.sumerge.survey.entity.SurveyForm;
import com.sumerge.survey.enumeration.SectionState;
import com.sumerge.survey.response.FormDetailsResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveyFormMapper {
    @Autowired
    private ModelMapper modelMapper;

//    public FormDetailsResponse mapToDto(SurveyForm surveyForm) {
//        surveyForm = applyPrePersistLogic(surveyForm);
//        return modelMapper.map(surveyForm, FormDetailsResponse.class);
//    }
//
//    private SurveyForm applyPrePersistLogic(SurveyForm surveyForm) {
//        if (surveyForm.getEnvironmentalSection() == null) {
//            surveyForm.setEnvironmentalSection(SectionState.OPENED_UNTOUCHED);
//        }
//        if (surveyForm.getSocialSection() == null) {
//            surveyForm.setSocialSection(SectionState.UNOPENED);
//        }
//        if (surveyForm.getGovernmentalSection() == null) {
//            surveyForm.setGovernmentalSection(SectionState.UNOPENED);
//        }
//        return surveyForm;
//
//    }
}
