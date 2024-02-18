package com.sumerge.survey.repository;

import com.sumerge.survey.entity.SurveyAnalytic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyFormRepository extends JpaRepository<SurveyAnalytic, Long> {
    List findByCompanyIdAndDuratingConfigId(Long companyId, Long duratingConfigId);
}