package com.fst.atsmasterdataservice.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BootcampDTO {

    private Long id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private int candidateNumber;
    private String skillRequired;
    private String languageRequired;

    private String languageLevelRequired;

    private TestDTO test;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(int candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    public String getSkillRequired() {
        return skillRequired;
    }

    public void setSkillRequired(String skillRequired) {
        this.skillRequired = skillRequired;
    }

    public String getLanguageRequired() {
        return languageRequired;
    }

    public void setLanguageRequired(String languageRequired) {
        this.languageRequired = languageRequired;
    }

    public String getLanguageLevelRequired() {
        return languageLevelRequired;
    }

    public void setLanguageLevelRequired(String languageLevelRequired) {
        this.languageLevelRequired = languageLevelRequired;
    }

    public TestDTO getTest() {
        return test;
    }

    public void setTest(TestDTO test) {
        this.test = test;
    }
}
