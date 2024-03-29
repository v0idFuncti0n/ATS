package com.fst.atsmasterdataservice.mapper;

import com.fst.atsmasterdataservice.dto.CandidateDTO;
import com.fst.atsmasterdataservice.entity.CandidateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    CandidateEntity dtoToEntity(CandidateDTO dto);

    CandidateDTO entityToDTO(CandidateEntity entity);
}
