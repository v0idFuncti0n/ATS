package com.fst.atsmasterdataservice.repository.candidate;

import com.fst.atsmasterdataservice.entity.candidate.CandidateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends CrudRepository<CandidateEntity, Long> {
    Optional<CandidateEntity> findById(Long id);
    List<CandidateEntity> findAll();
}
