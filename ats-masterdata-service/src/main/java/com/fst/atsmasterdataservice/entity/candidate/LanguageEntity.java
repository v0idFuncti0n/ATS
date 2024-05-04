package com.fst.atsmasterdataservice.entity.candidate;

import com.fst.atsmasterdataservice.enums.LanguageLevel;
import jakarta.persistence.*;


@Entity
@Table(name = "languages")
public class LanguageEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String language;
    private LanguageLevel level;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private CandidateEntity candidate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LanguageLevel getLevel() {
        return level;
    }

    public void setLevel(LanguageLevel level) {
        this.level = level;
    }

    public CandidateEntity getCandidate() {
        return candidate;
    }

    public void setCandidate(CandidateEntity candidate) {
        this.candidate = candidate;
    }
}
