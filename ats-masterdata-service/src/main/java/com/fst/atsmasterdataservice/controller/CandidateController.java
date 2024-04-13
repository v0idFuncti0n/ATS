package com.fst.atsmasterdataservice.controller;

import com.fst.atsmasterdataservice.service.CandidateService;
import com.fst.atsmasterdataservice.dto.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
