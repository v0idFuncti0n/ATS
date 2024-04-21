package com.fst.atsmasterdataservice.dto;

public class TestInfoDTO {

    private int technicalNote;
    private int interviewNote;

    private boolean noteInserted;

    public int getTechnicalNote() {
        return technicalNote;
    }

    public void setTechnicalNote(int technicalNote) {
        this.technicalNote = technicalNote;
    }

    public int getInterviewNote() {
        return interviewNote;
    }

    public void setInterviewNote(int interviewNote) {
        this.interviewNote = interviewNote;
    }

    public boolean isNoteInserted() {
        return noteInserted;
    }

    public void setNoteInserted(boolean noteInserted) {
        this.noteInserted = noteInserted;
    }
}
