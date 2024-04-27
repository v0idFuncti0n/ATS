package com.fst.atsmasterdataservice.entity;

import com.fst.atsmasterdataservice.entity.candidate.WorkExperienceEntity;
import com.fst.atsmasterdataservice.enums.TestStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tests")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    private Date startDate;
    private Date endDate;
    private int candidateNumber;

    private TestStatus status;

    @OneToOne(mappedBy = "test")
    private BootcampEntity bootcamp;

    @OneToMany(mappedBy="test", cascade = CascadeType.ALL)
    private List<TestInfoEntity> testInfoList = new ArrayList<>();

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

    public TestStatus getStatus() {
        return status;
    }

    public void setStatus(TestStatus status) {
        this.status = status;
    }

    public BootcampEntity getBootcamp() {
        return bootcamp;
    }

    public void setBootcamp(BootcampEntity bootcamp) {
        this.bootcamp = bootcamp;
    }

    public List<TestInfoEntity> getTestInfoList() {
        return testInfoList;
    }

    public void setTestInfoList(List<TestInfoEntity> testInfoList) {
        this.testInfoList = testInfoList;
    }
}
