package com.fst.atsmasterdataservice.repository;

import com.fst.atsmasterdataservice.entity.EducationEntity;
import com.fst.atsmasterdataservice.entity.SkillEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SkillRepository extends CrudRepository<SkillEntity, Long> {
    Optional<SkillEntity> findById(Long id);
}
