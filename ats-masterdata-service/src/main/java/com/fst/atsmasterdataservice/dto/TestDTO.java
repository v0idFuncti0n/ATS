package com.fst.atsmasterdataservice.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TestDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private int candidateNumber;

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
}
