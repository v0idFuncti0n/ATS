package com.fst.atsmasterdataservice.mapper;

import com.fst.atsmasterdataservice.dto.candidate.CandidateDTO;
import com.fst.atsmasterdataservice.entity.candidate.CandidateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    @Mappings({
            @Mapping(target = "version", ignore = true)
    })
    CandidateEntity dtoToEntity(CandidateDTO dto);

    CandidateDTO entityToDTO(CandidateEntity entity);
}
