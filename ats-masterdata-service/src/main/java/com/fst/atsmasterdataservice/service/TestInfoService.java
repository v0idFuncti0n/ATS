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

    public TestInfoDTO createTestInfo(TestInfoDTO testInfoDTO, Long testId, Long candidateId) {
        TestInfoEntity testInfo = testInfoMapper.dtoToEntity(testInfoDTO);

        TestEntity test = testRepository.findById(testId).get();
        testInfo.setTest(test);

        List<TestInfoEntity> testInfoList = test.getTestInfoList();
        testInfoList.add(testInfo);

        test.setTestInfoList(testInfoList);

        CandidateEntity candidate = candidateRepository.findById(candidateId).get();
        testInfo.setCandidate(candidate);

        testRepository.save(test);
        return testInfoMapper.entityToDTO(testInfo);
    }
}
