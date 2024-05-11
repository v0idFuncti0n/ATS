package com.fst.atsmasterdataservice.controller;

import com.fst.atsmasterdataservice.dto.TestDTO;
import com.fst.atsmasterdataservice.dto.TestInfoDTO;
import com.fst.atsmasterdataservice.service.TestInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class TestInfoController {

    private final TestInfoService testInfoService;

    @Autowired
    public TestInfoController(TestInfoService testInfoService) {
        this.testInfoService = testInfoService;
    }

    @PostMapping(path = "/testInfo/{testInfoId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestInfoDTO> updateTestInfo(@RequestBody TestInfoDTO testInfoDTO, @PathVariable Long testInfoId) {
        TestInfoDTO testInfo = testInfoService.updateTestInfo(testInfoDTO, testInfoId);
        return ResponseEntity.ok(testInfo);
    }

    @GetMapping(path = "/testInfo/test/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TestInfoDTO>> getTestInfoByTestId(@PathVariable("id") Long id) throws IOException {
        List<TestInfoDTO> testInfos = testInfoService.getTestInfoByTestId(id);
        return ResponseEntity.ok(testInfos);
    }
}
