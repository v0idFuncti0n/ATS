package com.fst.atsmasterdataservice.repository;


import com.fst.atsmasterdataservice.entity.BootcampEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BootcampRepository extends CrudRepository<BootcampEntity, Long> {

}