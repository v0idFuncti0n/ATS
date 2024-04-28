package com.fst.atsmasterdataservice.controller;

import com.fst.atsmasterdataservice.service.CandidateService;
import com.fst.atsmasterdataservice.dto.candidate.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping(path = "/candidates/{filename}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidateDTO> createCandidate(@RequestBody CandidateDTO candidateDTO, @RequestParam("filename") String resumeFilename) {
        CandidateDTO savedCandidateDTO = candidateService.createCandidate(candidateDTO, resumeFilename);
        return ResponseEntity.ok(savedCandidateDTO);
    }

    @GetMapping(path = "/candidates/{id}/resume", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getCandidateResumeFile(@PathVariable("id") Long id) throws IOException {
        Resource resumeFile = candidateService.getCandidateResumeFile(id);
        return ResponseEntity.ok(resumeFile);
    }

    @PostMapping(path = "/candidates/{candidateId}/verify", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidateDTO> verifyCandidate(@RequestBody CandidateDTO candidate, @PathVariable Long candidateId) {
        CandidateDTO candidateVerified = candidateService.verifyCandidate(candidate, candidateId);
        return ResponseEntity.ok(candidateVerified);
    }

    @GetMapping(path = "/candidates/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable("id") Long id) throws IOException {
        CandidateDTO candidate = candidateService.getCandidateById(id);
        return ResponseEntity.ok(candidate);
    }

    @PostMapping(path = "/candidates/{candidateId}/test/{testId}/refuse", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidateDTO> candidateRefusedTest(@PathVariable Long testId, @PathVariable Long candidateId) {
        CandidateDTO candidateDTO = candidateService.refuseTest(candidateId, testId);
        return ResponseEntity.ok(candidateDTO);
    }

    @GetMapping(path = "/candidates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CandidateDTO>> getCandidates() throws IOException {
        List<CandidateDTO> candidates = candidateService.getCandidates();
        return ResponseEntity.ok(candidates);
    }

}
