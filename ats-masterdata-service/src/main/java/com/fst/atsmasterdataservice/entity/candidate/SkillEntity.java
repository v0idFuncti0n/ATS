package com.fst.atsmasterdataservice.entity.candidate;

import jakarta.persistence.*;

@Entity
@Table(name = "skills")

public class SkillEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String skill;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "candidate_id")
    private CandidateEntity candidate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                ", skill='" + skill + '\'' +
                ", candidate=" + candidate.getId() +
                '}';
    }
}
