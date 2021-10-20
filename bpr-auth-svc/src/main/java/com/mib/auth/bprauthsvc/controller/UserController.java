package com.mib.auth.bprauthsvc.controller;


import com.mib.auth.bprauthsvc.dto.UserDto;
import com.mib.auth.bprauthsvc.dto.request.LoginRequestModel;
import com.mib.auth.bprauthsvc.dto.request.UserRequestModel;
import com.mib.auth.bprauthsvc.dto.response.UserResponseModel;
import com.mib.auth.bprauthsvc.services.UserServices;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/")
@Log4j2
public class UserController {

    @Autowired
    private Environment environment;

    @Autowired
    private UserServices userServices;

    @GetMapping("/status/check")
    public ResponseEntity<String> status() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info(authentication.getName() + "--" + authentication.getAuthorities().contains("Authorization"));
        return ResponseEntity.status(HttpStatus.OK).body("Working..." + environment.getProperty("local.server.port") + "--"
                + environment.getProperty("spring.cloud.client.ip-address"));
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> registerUser(@RequestBody LoginRequestModel registerRequest) {
        return ResponseEntity.status(HttpStatus.OK).body("Sukess");
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseModel> createUser(@RequestBody UserRequestModel userRequestModel) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);

        UserDto createdUser = userServices.createUser(userDto);

        UserResponseModel returnValue = modelMapper.map(createdUser, UserResponseModel.class);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnValue);
    }
}
