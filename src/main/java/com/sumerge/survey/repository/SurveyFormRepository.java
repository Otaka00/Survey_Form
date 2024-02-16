package com.sumerge.survey.repository;

import com.sumerge.survey.entity.SurveyForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SurveyFormRepository extends JpaRepository<SurveyForm, Long> {

}