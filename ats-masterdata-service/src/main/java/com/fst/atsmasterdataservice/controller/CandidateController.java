package com.fst.atsmasterdataservice.controller;

import com.fst.atsmasterdataservice.CandidateService;
import com.fst.atsmasterdataservice.dto.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping(path = "/candidate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidateDTO> createCandidate(@RequestBody CandidateDTO candidateDTO) {
        CandidateDTO savedCandidateDTO = candidateService.createCandidate(candidateDTO);
        System.out.println(savedCandidateDTO.getFirstName());
        return ResponseEntity.ok(savedCandidateDTO);
    }

}
