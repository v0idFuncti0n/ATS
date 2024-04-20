package com.fst.atsmasterdataservice.repository.candidate;

import com.fst.atsmasterdataservice.entity.candidate.CandidateEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

public interface CandidateRepository extends CrudRepository<CandidateEntity, Long> {
    Optional<CandidateEntity> findById(Long id);
}
