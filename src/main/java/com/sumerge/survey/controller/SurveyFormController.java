package com.sumerge.survey.controller;

import com.sumerge.survey.dto.FormRequestDTO;
import com.sumerge.survey.dto.FormDetailsResponseDTO;
import com.sumerge.survey.service.SurveyFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/survey-form")
public class SurveyFormController {

    Logger logger = LoggerFactory.getLogger(SurveyFormController.class);

    @Autowired
    private SurveyFormService surveyFormService;

    @GetMapping("/{companyId}/{duratingConfigId}")
    public ResponseEntity<FormDetailsResponseDTO> getFormDetails(@PathVariable Long companyId, @PathVariable Long duratingConfigId) {
        FormDetailsResponseDTO formDetails = surveyFormService.getFormDetails(companyId, duratingConfigId);
        return ResponseEntity.ok(formDetails);
    }

    @PostMapping("/create-form")
    public ResponseEntity<String> createForm(@RequestBody FormRequestDTO createFormRequest) {
        surveyFormService.createOrUpdate(createFormRequest.getCompanyId(),createFormRequest.getDuratingConfigId(),createFormRequest.getSectionStates());
        return ResponseEntity.ok("Form created successfully.");
    }

    @PutMapping("/update-form")
    public ResponseEntity<String> updateForm(@RequestBody FormRequestDTO updateFormRequest) {
        surveyFormService.createOrUpdate(updateFormRequest.getCompanyId(),updateFormRequest.getDuratingConfigId(),updateFormRequest.getSectionStates());
        return ResponseEntity.ok("Form updated successfully.");
    }
}
