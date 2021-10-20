package com.mib.auth.bprauthsvc.services;

import com.mib.auth.bprauthsvc.dto.UserDto;
import com.mib.auth.bprauthsvc.entities.UserEntity;
import com.mib.auth.bprauthsvc.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserServices {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        if (userDto.getScope() == null || userDto.getScope().isBlank() || userDto.getScope().isEmpty()){
            userEntity.setScope("WEB");
        }else {
            userEntity.setScope(userDto.getScope());
        }
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        userRepository.save(userEntity);

        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //UserEntity userEntity = userRepository.findByEmail(username);
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null)
            throw new UsernameNotFoundException(username);

        return new User(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true,
                new ArrayList<>());
    }

    @Override
    public UserDto getUserDetailsByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null)
            throw new UsernameNotFoundException(username);
        return new ModelMapper().map(userEntity, UserDto.class);
    }


}
