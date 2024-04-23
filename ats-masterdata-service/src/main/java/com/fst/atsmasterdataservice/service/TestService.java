package com.fst.atsmasterdataservice.service;

import com.fst.atsmasterdataservice.dto.TestDTO;
import com.fst.atsmasterdataservice.entity.BootcampEntity;
import com.fst.atsmasterdataservice.entity.TestEntity;
import com.fst.atsmasterdataservice.entity.TestInfoEntity;
import com.fst.atsmasterdataservice.entity.candidate.CandidateEntity;
import com.fst.atsmasterdataservice.enums.CandidateStatus;
import com.fst.atsmasterdataservice.mapper.TestMapper;
import com.fst.atsmasterdataservice.repository.BootcampRepository;
import com.fst.atsmasterdataservice.repository.TestInfoRepository;
import com.fst.atsmasterdataservice.repository.TestRepository;
import com.fst.atsmasterdataservice.repository.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;

    private final TestInfoRepository testInfoRepository;
    private final BootcampRepository bootcampRepository;

    private final CandidateRepository candidateRepository;
    private final TestMapper testMapper;

    @Autowired
    public TestService(TestRepository testRepository, TestInfoRepository testInfoRepository, BootcampRepository bootcampRepository, CandidateRepository candidateRepository, TestMapper testMapper) {
        this.testRepository = testRepository;
        this.testInfoRepository = testInfoRepository;
        this.bootcampRepository = bootcampRepository;
        this.candidateRepository = candidateRepository;
        this.testMapper = testMapper;
    }

    public TestDTO createTest(TestDTO testDTO, Long bootcampId) {
        TestEntity testEntity = testMapper.dtoToEntity(testDTO);

        BootcampEntity bootcampEntity = bootcampRepository.findById(bootcampId).get();
        bootcampEntity.setTest(testEntity);
        BootcampEntity bootcamp = bootcampRepository.save(bootcampEntity);

        List<CandidateEntity> candidateEntities = candidateRepository.findAll();

        List<CandidateEntity> candidateEntitiesFilteredByInPoolStatus = candidateEntities.stream().filter(candidateEntity -> candidateEntity.getStatus().equals(CandidateStatus.IN_POOL)).toList();
        System.out.println("total candidates: " + candidateEntitiesFilteredByInPoolStatus.size());

        List<CandidateEntity> candidateEntitiesFilteredBySkill = candidateEntitiesFilteredByInPoolStatus.stream().filter(candidateEntity -> candidateEntity.hasSkill(bootcamp.getSkillRequired())).toList();
        System.out.println("total candidates: " + candidateEntitiesFilteredBySkill.size());

        List<CandidateEntity> candidateEntitiesFilteredByLanguage = candidateEntitiesFilteredBySkill.stream().filter(candidateEntity -> candidateEntity.hasLanguage(bootcamp.getLanguageRequired(), bootcamp.getLanguageLevelRequired())).toList();
        System.out.println("total candidates: " + candidateEntitiesFilteredByLanguage.size());

        List<CandidateEntity> candidateEntitiesFilteredByAvailableTestNumber = candidateEntitiesFilteredByLanguage.stream().limit(testEntity.getCandidateNumber()).toList();
        System.out.println("total candidates: " + candidateEntitiesFilteredByAvailableTestNumber.size());

        List<TestInfoEntity> testInfoEntities = new ArrayList<>();
        candidateEntitiesFilteredByAvailableTestNumber.forEach(candidateEntity -> {
            TestInfoEntity testInfoEntity = new TestInfoEntity();
            testInfoEntity.setTest(testEntity);
            testInfoEntity.setCandidate(candidateEntity);
            testInfoEntity.setInterviewNote(0);
            testInfoEntity.setInterviewNote(0);
            testInfoEntity.setNoteInserted(false);

            testInfoEntities.add(testInfoEntity);

            candidateEntity.setStatus(CandidateStatus.IN_TEST);
            candidateRepository.save(candidateEntity);
        });

        testEntity.setTestInfoList(testInfoEntities);
        testRepository.save(testEntity);

        return testMapper.entityToDTO(testEntity);
    }


}
