package com.sumerge.survey.controller;

import com.sumerge.survey.request.CreateFormRequest;
import com.sumerge.survey.request.SectionStateRequest;
import com.sumerge.survey.entity.SurveyForm;
import com.sumerge.survey.request.UpdateFormRequest;
import com.sumerge.survey.response.FormDetailsResponse;
import com.sumerge.survey.service.SurveyFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/survey-form")
public class SurveyFormController {
    @Autowired
    private SurveyFormService surveyFormService;

    @GetMapping("/{formId}")
    public ResponseEntity<FormDetailsResponse> getFormDetails(@PathVariable long formId) {
        FormDetailsResponse formDetails = surveyFormService.getFormDetails(formId);
        return ResponseEntity.ok(formDetails);
    }

    @PostMapping("/create-form")
    public ResponseEntity<String> createForm(@RequestBody CreateFormRequest createFormRequest) {
        surveyFormService.createNewForm(
                createFormRequest.getSectionStates()
        );
        return ResponseEntity.ok("Form created successfully.");
    }

    @PutMapping("/update-form")
    public ResponseEntity<String> updateForm(@RequestBody UpdateFormRequest updateFormRequest) {
        surveyFormService.updateForm(
                updateFormRequest.getFormId(),
                updateFormRequest.getSectionStates()
        );
        return ResponseEntity.ok("Form updated successfully.");
    }
}
