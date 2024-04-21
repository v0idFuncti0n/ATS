package com.fst.atsmasterdataservice.repository.candidate;

import com.fst.atsmasterdataservice.entity.candidate.WorkExperienceEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface WorkExperienceRepository extends CrudRepository<WorkExperienceEntity, Long> {
    Optional<WorkExperienceEntity> findById(Long id);

    @Modifying
    @Query("delete from WorkExperienceEntity w where w.id = ?1")
    void deleteById(Long id);
}
