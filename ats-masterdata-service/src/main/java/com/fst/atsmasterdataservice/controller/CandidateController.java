package com.fst.atsmasterdataservice.controller;

import com.fst.atsmasterdataservice.service.CandidateService;
import com.fst.atsmasterdataservice.dto.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping(path = "/candidate/{filename}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidateDTO> createCandidate(@RequestBody CandidateDTO candidateDTO, @RequestParam("filename") String resumeFilename) {
        CandidateDTO savedCandidateDTO = candidateService.createCandidate(candidateDTO, resumeFilename);
        return ResponseEntity.ok(savedCandidateDTO);
    }

    @GetMapping(path = "/candidate/{id}/resume", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getCandidateResumeFile(@PathVariable("id") Long id) throws IOException {
        Resource resumeFile = candidateService.getCandidateResumeFile(id);
        return ResponseEntity.ok(resumeFile);
    }

}
