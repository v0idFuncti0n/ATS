package com.fst.atsmasterdataservice.dto;

import com.fst.atsmasterdataservice.dto.candidate.CandidateDTO;

public class TestInfoDTO {

    private Long id;
    private float technicalNote;
    private float interviewNote;

    private float finalNote;

    private CandidateDTO candidate;

    public float getTechnicalNote() {
        return technicalNote;
    }

    public void setTechnicalNote(float technicalNote) {
        this.technicalNote = technicalNote;
    }

    public float getInterviewNote() {
        return interviewNote;
    }

    public void setInterviewNote(float interviewNote) {
        this.interviewNote = interviewNote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getFinalNote() {
        return finalNote;
    }

    public void setFinalNote(float finalNote) {
        this.finalNote = finalNote;
    }
    public CandidateDTO getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateDTO candidate) {
        this.candidate = candidate;
    }

}
