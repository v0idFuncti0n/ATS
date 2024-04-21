package com.fst.atsmasterdataservice.entity;

import com.fst.atsmasterdataservice.entity.candidate.CandidateEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "test_infos")
public class TestInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    private int technicalNote;
    private int interviewNote;

    private boolean noteInserted;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id")
    private TestEntity test;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private CandidateEntity candidate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

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

    public TestEntity getTest() {
        return test;
    }

    public void setTest(TestEntity test) {
        this.test = test;
    }

    public CandidateEntity getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateEntity candidate) {
        this.candidate = candidate;
    }
}
