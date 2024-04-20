package com.fst.atsmasterdataservice.service;

import com.fst.atsmasterdataservice.dto.TestDTO;
import com.fst.atsmasterdataservice.entity.BootcampEntity;
import com.fst.atsmasterdataservice.entity.TestEntity;
import com.fst.atsmasterdataservice.mapper.TestMapper;
import com.fst.atsmasterdataservice.repository.BootcampRepository;
import com.fst.atsmasterdataservice.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final TestRepository testRepository;
    private final BootcampRepository bootcampRepository;
    private final TestMapper testMapper;

    @Autowired
    public TestService(TestRepository testRepository, BootcampRepository bootcampRepository, TestMapper testMapper) {
        this.testRepository = testRepository;
        this.bootcampRepository = bootcampRepository;
        this.testMapper = testMapper;
    }

    public TestDTO createTest(TestDTO testDTO, Long bootcampId) {
        TestEntity testEntity = testMapper.dtoToEntity(testDTO);

        BootcampEntity bootcampEntity = bootcampRepository.findById(bootcampId).get();
        bootcampEntity.setTest(testEntity);
        bootcampRepository.save(bootcampEntity);

        return testMapper.entityToDTO(testEntity);
    }


}
