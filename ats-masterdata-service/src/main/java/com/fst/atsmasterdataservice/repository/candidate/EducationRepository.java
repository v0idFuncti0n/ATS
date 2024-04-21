package com.fst.atsmasterdataservice.repository.candidate;

import com.fst.atsmasterdataservice.entity.candidate.EducationEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepository extends CrudRepository<EducationEntity, Long> {
    Optional<EducationEntity> findById(Long id);

    @Modifying
    @Query("delete from EducationEntity e where e.id = ?1")
    void deleteById(Long id);
}
