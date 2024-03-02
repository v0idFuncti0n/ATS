package com.fst.atsmasterdataservice.service;

import com.fst.atsmasterdataservice.dto.CandidateDTO;
import com.fst.atsmasterdataservice.entity.CandidateEntity;
import com.fst.atsmasterdataservice.mapper.CandidateMapper;
import com.fst.atsmasterdataservice.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final WorkExperienceRepository workExperienceRepository;
    private final EducationRepository educationRepository;
    private final SkillRepository skillRepository;
    private final LanguageRepository languageRepository;
    private final CandidateMapper candidateMapper;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository, WorkExperienceRepository workExperienceRepository, EducationRepository educationRepository, SkillRepository skillRepository, LanguageRepository languageRepository, CandidateMapper candidateMapper) {
        this.candidateRepository = candidateRepository;
        this.workExperienceRepository = workExperienceRepository;
        this.educationRepository = educationRepository;
        this.skillRepository = skillRepository;
        this.languageRepository = languageRepository;
        this.candidateMapper = candidateMapper;
    }

    public CandidateDTO createCandidate(CandidateDTO candidateDTO) {
        CandidateEntity candidateEntity = candidateMapper.dtoToEntity(candidateDTO);
        CandidateEntity savedCandidateEntity = candidateRepository.save(candidateEntity);

        savedCandidateEntity.getWorkExperiences().forEach(workExperienceEntity -> {
            workExperienceEntity.setCandidate(savedCandidateEntity);
            workExperienceRepository.save(workExperienceEntity);
        });

        savedCandidateEntity.getEducations().forEach(educationEntity -> {
            educationEntity.setCandidate(savedCandidateEntity);
            educationRepository.save(educationEntity);
        });

        savedCandidateEntity.getSkills().forEach(skillEntity -> {
            skillEntity.setCandidate(savedCandidateEntity);
            skillRepository.save(skillEntity);
        });

        savedCandidateEntity.getLanguages().forEach(languageEntity -> {
            languageEntity.setCandidate(savedCandidateEntity);
            languageRepository.save(languageEntity);
        });

        return candidateMapper.entityToDTO(savedCandidateEntity);
    }
}
