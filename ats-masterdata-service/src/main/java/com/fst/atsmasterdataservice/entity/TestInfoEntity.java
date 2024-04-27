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

    @Column(precision=2)
    private float technicalNote;
    @Column(precision=2)
    private float interviewNote;

    @Column(precision=2)
    private float finalNote;

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

    public float getFinalNote() {
        return finalNote;
    }

    public void setFinalNote(float finalNote) {
        this.finalNote = finalNote;
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
