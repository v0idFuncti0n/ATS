package com.fst.atsmasterdataservice.service;

import com.fst.atsmasterdataservice.dto.UserDTO;
import com.fst.atsmasterdataservice.entity.candidate.UserEntity;
import com.fst.atsmasterdataservice.mapper.UserMapper;
import com.fst.atsmasterdataservice.repository.UserRepository;
import com.fst.atsmasterdataservice.request.AuthenticationRequest;
import com.fst.atsmasterdataservice.util.JWTUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO authenticate(AuthenticationRequest request) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(request.getUsername());
        if(userEntityOptional.isPresent()) {
            UserEntity user = userEntityOptional.get();
            if(user.getPassword().equals(request.getPassword())){
                String token = JWTUtil.generateToken(user.getUsername());
                UserDTO userDTO = userMapper.entityToDTO(user);
                userDTO.setRole(user.getRole());
                userDTO.setToken(token);
                return userDTO;
            }
        }
        return null;
    }
}
