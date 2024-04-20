package com.fst.atsmasterdataservice.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BootcampDTO {

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private int candidateNumber;
    private String skillsRequiredTags;
    private String languagesRequiredTags;

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

    public String getSkillsRequiredTags() {
        return skillsRequiredTags;
    }

    public void setSkillsRequiredTags(String skillsRequiredTags) {
        this.skillsRequiredTags = skillsRequiredTags;
    }

    public String getLanguagesRequiredTags() {
        return languagesRequiredTags;
    }

    public void setLanguagesRequiredTags(String languagesRequiredTags) {
        this.languagesRequiredTags = languagesRequiredTags;
    }
}
