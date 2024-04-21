package com.fst.atsmasterdataservice.service;

import com.fst.atsmasterdataservice.dto.candidate.CandidateDTO;
import com.fst.atsmasterdataservice.entity.candidate.CandidateEntity;
import com.fst.atsmasterdataservice.feign.ATSResumeParserFeignClient;
import com.fst.atsmasterdataservice.mapper.CandidateMapper;
import com.fst.atsmasterdataservice.repository.candidate.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final WorkExperienceRepository workExperienceRepository;
    private final EducationRepository educationRepository;
    private final SkillRepository skillRepository;
    private final LanguageRepository languageRepository;
    private final CandidateMapper candidateMapper;
    private final ATSResumeParserFeignClient atsResumeParserFeignClient;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository, WorkExperienceRepository workExperienceRepository, EducationRepository educationRepository, SkillRepository skillRepository, LanguageRepository languageRepository, CandidateMapper candidateMapper, ATSResumeParserFeignClient atsResumeParserFeignClient) {
        this.candidateRepository = candidateRepository;
        this.workExperienceRepository = workExperienceRepository;
        this.educationRepository = educationRepository;
        this.skillRepository = skillRepository;
        this.languageRepository = languageRepository;
        this.candidateMapper = candidateMapper;
        this.atsResumeParserFeignClient = atsResumeParserFeignClient;
    }

    public CandidateDTO createCandidate(CandidateDTO candidateDTO, String resumeFilename) {
        candidateDTO.setResumeFilename(resumeFilename);

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

    public Resource getCandidateResumeFile(Long id) {
        CandidateDTO candidate = candidateMapper.entityToDTO(candidateRepository.findById(id).get());
        return atsResumeParserFeignClient.getResumeFile(candidate.getResumeFilename()).getBody();
    }

    public CandidateDTO verifyCandidate(CandidateDTO candidate, Long candidateId) {
        CandidateEntity candidateToUpdateEntity = candidateRepository.findById(candidateId).get();
        candidateToUpdateEntity.getSkills().forEach(skillEntity -> {
            skillEntity.setCandidate(null);
            skillRepository.deleteById(skillEntity.getId());
        });

        candidateToUpdateEntity.getEducations().forEach(educationEntity -> {
            educationEntity.setCandidate(null);
            educationRepository.deleteById(educationEntity.getId());
        });

        candidateToUpdateEntity.getWorkExperiences().forEach(workExperienceEntity -> {
            workExperienceEntity.setCandidate(null);
            workExperienceRepository.deleteById(workExperienceEntity.getId());
        });

        candidateToUpdateEntity.getLanguages().forEach(languageEntity -> {
            languageEntity.setCandidate(null);
            languageRepository.deleteById(languageEntity.getId());
        });

        CandidateDTO candidateToUpdateDTO = candidateMapper.entityToDTO(candidateToUpdateEntity);
        BeanUtils.copyProperties(candidate, candidateToUpdateDTO);
        candidateToUpdateDTO.setVerified(true);
        candidateToUpdateDTO.setId(candidateId);

        CandidateEntity candidateVerified = candidateMapper.dtoToEntity(candidateToUpdateDTO);
        candidateVerified.getSkills().forEach(skillEntity -> {
            skillEntity.setCandidate(candidateVerified);
        });

        return candidateMapper.entityToDTO(candidateRepository.save(candidateVerified));
    }
}
