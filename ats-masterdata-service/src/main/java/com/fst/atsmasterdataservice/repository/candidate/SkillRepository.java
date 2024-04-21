package com.fst.atsmasterdataservice.repository.candidate;

import com.fst.atsmasterdataservice.entity.candidate.SkillEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends CrudRepository<SkillEntity, Long> {
    Optional<SkillEntity> findById(Long id);

    @Modifying
    @Query("delete from SkillEntity s where s.id = ?1")
    void deleteById(Long id);
}
