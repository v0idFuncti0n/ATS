package com.fst.atsmasterdataservice.entity;

import com.fst.atsmasterdataservice.entity.candidate.CandidateEntity;
import com.fst.atsmasterdataservice.enums.LanguageLevel;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bootcamps")
public class BootcampEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private int version;
    private String name;
    private Date startDate;
    private Date endDate;
    private int candidateNumber;
    private String skillRequired;
    private String languageRequired;

    private LanguageLevel languageLevelRequired;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private TestEntity test;

    @OneToMany(mappedBy="bootcamp")
    private List<CandidateEntity> candidates = new ArrayList<>();

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

    public void setSkillRequired(String skillsRequiredTags) {
        this.skillRequired = skillsRequiredTags;
    }

    public String getLanguageRequired() {
        return languageRequired;
    }

    public void setLanguageRequired(String languagesRequiredTags) {
        this.languageRequired = languagesRequiredTags;
    }

    public LanguageLevel getLanguageLevelRequired() {
        return languageLevelRequired;
    }

    public void setLanguageLevelRequired(LanguageLevel languageLevelRequired) {
        this.languageLevelRequired = languageLevelRequired;
    }

    public TestEntity getTest() {
        return test;
    }

    public void setTest(TestEntity test) {
        this.test = test;
    }

    public List<CandidateEntity> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<CandidateEntity> candidates) {
        this.candidates = candidates;
    }
}
