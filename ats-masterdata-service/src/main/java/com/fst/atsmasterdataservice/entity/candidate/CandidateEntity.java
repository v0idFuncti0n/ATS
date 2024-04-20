package com.fst.atsmasterdataservice.entity.candidate;

import com.fst.atsmasterdataservice.enums.CandidateStatus;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "candidates")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String location;
    private String birthDate;
    private String resumeFilename;
    private boolean verified;
    private CandidateStatus status;

    @OneToMany(mappedBy="candidate", cascade = CascadeType.ALL)
    private List<WorkExperienceEntity> workExperiences;

    @OneToMany(mappedBy="candidate", cascade = CascadeType.ALL)
    private List<EducationEntity> educations;

    @OneToMany(mappedBy="candidate", cascade = CascadeType.ALL)
    private List<LanguageEntity> languages;

    @OneToMany(mappedBy="candidate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SkillEntity> skills = new ArrayList<>();

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getResumeFilename() {
        return resumeFilename;
    }

    public void setResumeFilename(String resumeFilename) {
        this.resumeFilename = resumeFilename;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public CandidateStatus getStatus() {
        return status;
    }

    public void setStatus(CandidateStatus status) {
        this.status = status;
    }

    public List<WorkExperienceEntity> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperienceEntity> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public List<EducationEntity> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationEntity> educations) {
        this.educations = educations;
    }

    public List<LanguageEntity> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageEntity> languages) {
        this.languages = languages;
    }

    public List<SkillEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillEntity> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "CandidateEntity{" +
                "id=" + id +
                ", version=" + version +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", location='" + location + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", resumeFilename='" + resumeFilename + '\'' +
                ", verified=" + verified +
                ", status=" + status +
                ", workExperiences=" + workExperiences +
                ", educations=" + educations +
                ", languages=" + languages +
                ", skills=" + skills +
                '}';
    }
}
