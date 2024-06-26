package com.fst.atsmasterdataservice.mapper;

import com.fst.atsmasterdataservice.dto.candidate.CandidateDTO;
import com.fst.atsmasterdataservice.entity.candidate.CandidateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    CandidateEntity dtoToEntity(CandidateDTO dto);

    CandidateDTO entityToDTO(CandidateEntity entity);

    List<CandidateDTO> listEntityToDTO(List<CandidateEntity> candidate);
}
