package com.fst.atsmasterdataservice.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
    private String skillsRequiredTags;
    private String languagesRequiredTags;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    private TestEntity test;

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

    public TestEntity getTest() {
        return test;
    }

    public void setTest(TestEntity test) {
        this.test = test;
    }
}