package com.fst.atsmasterdataservice.mapper;

import com.fst.atsmasterdataservice.dto.BootcampDTO;
import com.fst.atsmasterdataservice.entity.BootcampEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BootcampMapper {

    BootcampEntity dtoToEntity(BootcampDTO dto);

    BootcampDTO entityToDTO(BootcampEntity entity);

    List<BootcampDTO> listEntityToDTO(List<BootcampEntity> bootcamp);
}
