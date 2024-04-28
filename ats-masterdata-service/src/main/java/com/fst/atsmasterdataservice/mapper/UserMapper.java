package com.fst.atsmasterdataservice.mapper;

import com.fst.atsmasterdataservice.dto.UserDTO;
import com.fst.atsmasterdataservice.entity.candidate.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity dtoToEntity(UserDTO dto);

    UserDTO entityToDTO(UserEntity entity);
}
