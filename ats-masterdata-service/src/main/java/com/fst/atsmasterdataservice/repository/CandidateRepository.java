package com.fst.atsmasterdataservice.repository;

import com.fst.atsmasterdataservice.entity.CandidateEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CandidateRepository extends CrudRepository<CandidateEntity, Long> {
    Optional<CandidateEntity> findById(Long id);
}
