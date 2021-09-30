package com.mib.auth.bprauthsvc.services;

import com.mib.auth.bprauthsvc.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService {

	UserDto createUser(UserDto userDto);

	UserDto getUserDetailsByEmail(String email);

}
