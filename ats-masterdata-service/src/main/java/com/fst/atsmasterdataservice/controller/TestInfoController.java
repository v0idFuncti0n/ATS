package com.fst.atsmasterdataservice.controller;

import com.fst.atsmasterdataservice.dto.TestInfoDTO;
import com.fst.atsmasterdataservice.service.TestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestInfoController {

    private final TestInfoService testInfoService;

    @Autowired
    public TestInfoController(TestInfoService testInfoService) {
        this.testInfoService = testInfoService;
    }

    @PostMapping(path = "/testInfo/{testId}/{candidateId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestInfoDTO> createTestInfo(@RequestBody TestInfoDTO testInfoDTO, @PathVariable Long testId, @PathVariable Long candidateId) {
        TestInfoDTO testInfo = testInfoService.createTestInfo(testInfoDTO, testId, candidateId);
        return ResponseEntity.ok(testInfo);
    }
}