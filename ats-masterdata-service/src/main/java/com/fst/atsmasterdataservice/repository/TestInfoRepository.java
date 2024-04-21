package com.fst.atsmasterdataservice.repository;

import com.fst.atsmasterdataservice.entity.BootcampEntity;
import com.fst.atsmasterdataservice.entity.TestInfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestInfoRepository extends CrudRepository<TestInfoEntity, Long> {
}
