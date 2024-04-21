package com.fst.atsmasterdataservice.entity.candidate;

import jakarta.persistence.*;

@Entity
@Table(name = "skills")

public class SkillEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Version
    private int version;

    private String skill;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "candidate_id")
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

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public CandidateEntity getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateEntity candidate) {
        this.candidate = candidate;
    }

    @Override
    public String toString() {
        return "SkillEntity{" +
                "id=" + id +
                ", version=" + version +
                ", skill='" + skill + '\'' +
                ", candidate=" + candidate.getId() +
                '}';
    }
}
