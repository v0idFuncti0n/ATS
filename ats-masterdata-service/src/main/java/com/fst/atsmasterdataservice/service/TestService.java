package com.fst.atsmasterdataservice.service;

import com.fst.atsmasterdataservice.dto.TestDTO;
import com.fst.atsmasterdataservice.dto.TestInfoDTO;
import com.fst.atsmasterdataservice.entity.BootcampEntity;
import com.fst.atsmasterdataservice.entity.TestEntity;
import com.fst.atsmasterdataservice.entity.TestInfoEntity;
import com.fst.atsmasterdataservice.entity.candidate.CandidateEntity;
import com.fst.atsmasterdataservice.enums.CandidateStatus;
import com.fst.atsmasterdataservice.enums.TestStatus;
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

    private final TestInfoService testInfoService;

    @Autowired
    public TestService(TestRepository testRepository, TestInfoRepository testInfoRepository, BootcampRepository bootcampRepository, CandidateRepository candidateRepository, TestMapper testMapper, TestInfoService testInfoService) {
        this.testRepository = testRepository;
        this.testInfoRepository = testInfoRepository;
        this.bootcampRepository = bootcampRepository;
        this.candidateRepository = candidateRepository;
        this.testMapper = testMapper;
        this.testInfoService = testInfoService;
    }

    public TestDTO createTest(TestDTO testDTO, Long bootcampId) {
        TestEntity testEntity = testMapper.dtoToEntity(testDTO);

        BootcampEntity bootcampEntity = bootcampRepository.findById(bootcampId).get();
        bootcampEntity.setTest(testEntity);
        BootcampEntity bootcamp = bootcampRepository.save(bootcampEntity);

        List<CandidateEntity> candidateEntities = candidateRepository.findAll();

        List<CandidateEntity> candidateEntitiesFiltered = getFilteredCandidates(candidateEntities, bootcamp, testEntity.getCandidateNumber());

        List<TestInfoEntity> testInfoEntities = new ArrayList<>();
        candidateEntitiesFiltered.forEach(candidateEntity -> {
            TestInfoEntity testInfoEntity = new TestInfoEntity();
            testInfoEntity.setTest(testEntity);
            testInfoEntity.setCandidate(candidateEntity);
            testInfoEntity.setInterviewNote(0);
            testInfoEntity.setInterviewNote(0);

            testInfoEntities.add(testInfoEntity);

            candidateEntity.setStatus(CandidateStatus.IN_TEST);
            candidateRepository.save(candidateEntity);
        });

        testEntity.setTestInfoList(testInfoEntities);
        testEntity.setStatus(TestStatus.IN_COMMUNICATION_WITH_CANDIDATES);
        testRepository.save(testEntity);

        return testMapper.entityToDTO(testEntity);
    }

    public TestDTO removeCandidateFromTest(Long testId, Long candidateId) {
        TestEntity testEntity = testRepository.findById(testId).get();

        List<TestInfoEntity> updatedTestInfoEntities = testInfoService.deleteTestInfoFromTest(testEntity.getId(), candidateId);
        testEntity.setTestInfoList(new ArrayList<>(updatedTestInfoEntities));

        TestEntity testUpdated = testRepository.save(testEntity);

        return testMapper.entityToDTO(testUpdated);
    }

    private List<CandidateEntity> getFilteredCandidates(List<CandidateEntity> candidateEntities, BootcampEntity bootcamp, int candidateNumber) {
        List<CandidateEntity> candidateEntitiesFilteredByInPoolStatus = candidateEntities.stream().filter(candidateEntity -> candidateEntity.getStatus().equals(CandidateStatus.IN_POOL)).toList();
        // System.out.println("total candidates: " + candidateEntitiesFilteredByInPoolStatus.size());

        List<CandidateEntity> candidateEntitiesFilteredByIsVerifiedStatus = candidateEntitiesFilteredByInPoolStatus.stream().filter(CandidateEntity::isVerified).toList();
        // System.out.println("total candidates: " + candidateEntitiesFilteredByIsVerifiedStatus.size());

        List<CandidateEntity> candidateEntitiesFilteredBySkill = candidateEntitiesFilteredByIsVerifiedStatus.stream().filter(candidateEntity -> candidateEntity.hasSkill(bootcamp.getSkillRequired())).toList();
        // System.out.println("total candidates: " + candidateEntitiesFilteredBySkill.size());

        List<CandidateEntity> candidateEntitiesFilteredByLanguage = candidateEntitiesFilteredBySkill.stream().filter(candidateEntity -> candidateEntity.hasLanguage(bootcamp.getLanguageRequired(), bootcamp.getLanguageLevelRequired())).toList();
        // System.out.println("total candidates: " + candidateEntitiesFilteredByLanguage.size());

        List<CandidateEntity> candidateEntitiesFilteredByAvailableNumber = candidateEntitiesFilteredByLanguage.stream().limit(candidateNumber).toList();
        System.out.println("total candidates: " + candidateEntitiesFilteredByAvailableNumber.size());

        return candidateEntitiesFilteredByAvailableNumber;

    }

    public void addCandidateInPoolToTest(List<CandidateEntity> allCandidates, TestEntity testEntity, int candidateNumber) {
        List<CandidateEntity> candidateEntities = getFilteredCandidates(allCandidates, testEntity.getBootcamp(), candidateNumber);
        candidateEntities.forEach(candidateEntity -> {
            TestInfoEntity testInfoEntity = new TestInfoEntity();
            testInfoEntity.setTest(testEntity);
            testInfoEntity.setCandidate(candidateEntity);
            testInfoEntity.setInterviewNote(0);
            testInfoEntity.setInterviewNote(0);

            testEntity.getTestInfoList().add(testInfoEntity);

            candidateEntity.setStatus(CandidateStatus.IN_TEST);
            candidateRepository.save(candidateEntity);
        });
        testRepository.save(testEntity);
        System.out.println("testInfo candidates: " + testEntity.getTestInfoList().size());
    }

    public TestDTO updateTestStatusInTesting(Long testId) {
        TestEntity testEntity = testRepository.findById(testId).get();
        testEntity.setStatus(TestStatus.IN_TESTING);
        testRepository.save(testEntity);
        return testMapper.entityToDTO(testEntity);
    }


    public TestDTO completeTest(Long testId) {
        TestEntity testEntity = testRepository.findById(testId).get();
        List<TestInfoEntity> bestCandidatesInfo = testInfoService.getBestCandidateInTest(testEntity);
        bestCandidatesInfo.forEach(testInfoEntity -> {
            System.out.println(testInfoEntity.getCandidate().getId() + " " + testInfoEntity.getFinalNote());
        });
        testEntity.setStatus(TestStatus.COMPLETED);
        testEntity = testRepository.save(testEntity);

        BootcampEntity bootcamp = testEntity.getBootcamp();
        List<CandidateEntity> candidateInBootcamp = new ArrayList<>();
        bestCandidatesInfo.forEach(testInfoEntity -> {
            CandidateEntity candidate = testInfoEntity.getCandidate();
            candidate.setStatus(CandidateStatus.IN_BOOTCAMP);
            candidate.setBootcamp(bootcamp);
            candidateRepository.save(candidate);
            candidateInBootcamp.add(candidate);
        });
        bootcamp.setCandidates(candidateInBootcamp);
        bootcampRepository.save(bootcamp);

        testEntity.getTestInfoList().forEach(testInfoEntity -> {
            CandidateEntity candidate = testInfoEntity.getCandidate();
            if(candidate.getStatus().equals(CandidateStatus.IN_TEST)) {
                candidate.setStatus(CandidateStatus.IN_POOL);
                candidateRepository.save(candidate);

                testInfoEntity.setCandidate(null);
                testInfoRepository.deleteById(testInfoEntity.getId());
            }
        });

        return testMapper.entityToDTO(testEntity);
    }

    public List<TestDTO> getTests() {
        List<TestEntity> tests = testRepository.findAll();
        return testMapper.listEntityToDTO(tests);
    }
}
