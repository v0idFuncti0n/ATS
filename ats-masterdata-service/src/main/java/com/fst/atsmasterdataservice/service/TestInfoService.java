package com.fst.atsmasterdataservice.service;

import com.fst.atsmasterdataservice.dto.TestInfoDTO;
import com.fst.atsmasterdataservice.entity.TestEntity;
import com.fst.atsmasterdataservice.entity.TestInfoEntity;
import com.fst.atsmasterdataservice.entity.candidate.CandidateEntity;
import com.fst.atsmasterdataservice.mapper.TestInfoMapper;
import com.fst.atsmasterdataservice.repository.TestInfoRepository;
import com.fst.atsmasterdataservice.repository.TestRepository;
import com.fst.atsmasterdataservice.repository.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class TestInfoService {

    private final TestInfoRepository testInfoRepository;
    private final TestRepository testRepository;
    private final CandidateRepository candidateRepository;
    private final TestInfoMapper testInfoMapper;

    @Autowired
    public TestInfoService(TestInfoRepository testInfoRepository, TestRepository testRepository, CandidateRepository candidateRepository, TestInfoMapper testInfoMapper) {
        this.testInfoRepository = testInfoRepository;
        this.testRepository = testRepository;
        this.candidateRepository = candidateRepository;
        this.testInfoMapper = testInfoMapper;
    }

    public TestInfoDTO updateTestInfo(TestInfoDTO testInfoDTO, Long testInfoId) {
        TestInfoEntity testInfoEntity = testInfoRepository.findById(testInfoId).get();

        testInfoEntity.setInterviewNote(testInfoDTO.getInterviewNote());
        testInfoEntity.setTechnicalNote(testInfoDTO.getTechnicalNote());

        float finalNote = (testInfoEntity.getInterviewNote() + testInfoEntity.getTechnicalNote()) / 2;
        testInfoEntity.setFinalNote(finalNote);
        testInfoEntity = testInfoRepository.save(testInfoEntity);

        return testInfoMapper.entityToDTO(testInfoEntity);
    }

    public List<TestInfoEntity> deleteTestInfoFromTest(Long testId, Long candidateId) {
        TestEntity testEntity = testRepository.findById(testId).get();
        List<TestInfoEntity> testInfoEntities = new ArrayList<>(testEntity.getTestInfoList());
        List<TestInfoEntity> testInfoEntitiesUpdated = new ArrayList<>();
        testInfoEntities.forEach(testInfoEntity -> {
            if(!(testInfoEntity.getCandidate().getId() == candidateId)) {
                testInfoEntitiesUpdated.add(testInfoEntity);
            } else {
                testInfoEntity.setCandidate(null);
                testInfoRepository.save(testInfoEntity);
                testInfoRepository.deleteById(testInfoEntity.getId());
            }
        });
        return testInfoEntitiesUpdated;
    }

    public List<TestInfoEntity> getBestCandidateInTest(TestEntity test) {
        List<TestInfoEntity> testInfoEntities = testInfoRepository.findByTestOrderByFinalNoteDescIdAsc(test);
        return testInfoEntities.stream().limit(test.getBootcamp().getCandidateNumber()).toList();
    }
}
