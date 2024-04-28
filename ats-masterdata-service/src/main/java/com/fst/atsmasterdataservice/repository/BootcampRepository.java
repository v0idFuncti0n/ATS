package com.fst.atsmasterdataservice.repository;


import com.fst.atsmasterdataservice.entity.BootcampEntity;
import com.fst.atsmasterdataservice.entity.TestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BootcampRepository extends CrudRepository<BootcampEntity, Long> {

    List<BootcampEntity> findAll();
}
