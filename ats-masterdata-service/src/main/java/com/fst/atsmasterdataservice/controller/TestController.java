package com.fst.atsmasterdataservice.controller;

import com.fst.atsmasterdataservice.dto.TestDTO;
import com.fst.atsmasterdataservice.dto.candidate.CandidateDTO;
import com.fst.atsmasterdataservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping(path = "/tests/{bootcampId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestDTO> createTest(@RequestBody TestDTO testDTO, @PathVariable Long bootcampId) {
        TestDTO test = testService.createTest(testDTO, bootcampId);
        return ResponseEntity.ok(test);
    }

    @PostMapping(path = "/tests/{testId}/inTesting", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestDTO> updateTestStatusInTesting(@PathVariable Long testId) {
        TestDTO test = testService.updateTestStatusInTesting(testId);
        return ResponseEntity.ok(test);
    }

    @PostMapping(path = "/tests/{testId}/completeTest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestDTO> completeTest(@PathVariable Long testId) {
        TestDTO test = testService.completeTest(testId);
        return ResponseEntity.ok(test);
    }

    @GetMapping(path = "/tests", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TestDTO>> getCandidates() throws IOException {
        List<TestDTO> tests = testService.getTests();
        return ResponseEntity.ok(tests);
    }

    @GetMapping(path = "/tests/{testId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestDTO> getTest(@PathVariable Long testId) {
        TestDTO testDTO = testService.getTestById(testId);
        return ResponseEntity.ok(testDTO);
    }

    @PutMapping(path = "/tests/{testId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestDTO> updateTest(@RequestBody TestDTO testDTO, @PathVariable Long testId) {
        TestDTO test = testService.updateTest(testDTO, testId);
        return ResponseEntity.ok(test);
    }

    @DeleteMapping(path = "/tests/{testId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteTest(@PathVariable Long testId) {
        testService.deleteTest(testId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
