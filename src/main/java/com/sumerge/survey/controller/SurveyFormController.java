package com.sumerge.survey.controller;

// SurveyFormController.java
import com.sumerge.survey.dto.SectionStateRequest;
import com.sumerge.survey.entity.SurveyForm;
import com.sumerge.survey.service.SurveyFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/survey-form")
public class SurveyFormController {
    @Autowired
    private SurveyFormService surveyFormService;

    @GetMapping("/{userId}")
    public List<SurveyForm> getUserSurveyForms(@PathVariable String userId) {
        return surveyFormService.getUserSurveyForms(userId);
    }

    @PostMapping("/record-section-state")
    public ResponseEntity<String> recordSectionState(@RequestBody SectionStateRequest request) {
        surveyFormService.recordSectionState(request.getUserId(), request.getSection(), request.getState());
        return ResponseEntity.ok("Section state recorded successfully.");
    }
}
