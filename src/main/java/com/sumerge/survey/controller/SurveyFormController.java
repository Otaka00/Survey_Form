package com.sumerge.survey.controller;

import com.sumerge.survey.dto.CreateFormRequestDTO;
import com.sumerge.survey.dto.UpdateFormRequestDTO;
import com.sumerge.survey.dto.FormDetailsResponseDTO;
import com.sumerge.survey.service.SurveyFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/survey-form")
public class SurveyFormController {
    @Autowired
    private SurveyFormService surveyFormService;

    @GetMapping("/{formId}")
    public ResponseEntity<FormDetailsResponseDTO> getFormDetails(@PathVariable long formId) {
        FormDetailsResponseDTO formDetails = surveyFormService.getFormDetails(formId);
        return ResponseEntity.ok(formDetails);
    }

    @PostMapping("/create-form")
    public ResponseEntity<String> createForm(@RequestBody CreateFormRequestDTO createFormRequest) {
        surveyFormService.createNewForm(
                createFormRequest.getSectionStates()
        );
        return ResponseEntity.ok("Form created successfully.");
    }

    @PutMapping("/update-form")
    public ResponseEntity<String> updateForm(@RequestBody UpdateFormRequestDTO updateFormRequest) {
        surveyFormService.updateForm(
                updateFormRequest.getFormId(),
                updateFormRequest.getSectionStates()
        );
        return ResponseEntity.ok("Form updated successfully.");
    }
}
