package com.mib.customer.bprcustomersvc.controller;

import com.mib.customer.bprcustomersvc.dto.request.*;
import com.mib.customer.bprcustomersvc.dto.response.*;
import com.mib.customer.bprcustomersvc.services.CustomerAuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth/")
@AllArgsConstructor
public class AuthController {


    private final Environment environment;
    private final CustomerAuthService customerAuthService;


    @GetMapping("/status/check")
    public ResponseEntity<String> status() {
        return ResponseEntity.status(HttpStatus.OK).body("Working..." + environment.getProperty("local.server.port") + "--"
                + environment.getProperty("spring.cloud.client.ip-address"));
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GeneralResponse<RegisterResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse = customerAuthService.register(registerRequest);
        return new GeneralResponse<RegisterResponse>().success(registerResponse);
    }

    @PostMapping(value = "/verify-otp", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GeneralResponse<VerifyResponse> verifyOtp(@RequestBody VerifyRequest verifyRequest) {
        VerifyResponse verifyResponse = customerAuthService.verifyOtp(verifyRequest);
        return new GeneralResponse<VerifyResponse>().success(verifyResponse);
    }

    @PostMapping(value = "/set-password", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GeneralResponse<PasswordSettingResponse> verifyOtp(@RequestBody PasswordSettingRequest passwordSettingRequest) {
        PasswordSettingResponse passwordSettingResponse = customerAuthService.setPassword(passwordSettingRequest);
        return new GeneralResponse<PasswordSettingResponse>().success(passwordSettingResponse);
    }


    @PostMapping(value = "/set-mpin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GeneralResponse<MpinResponse> setMpin(@RequestBody MpinRequest mpinRequest) {
        MpinResponse MpinResponse = customerAuthService.setMpin(mpinRequest);
        return new GeneralResponse<MpinResponse>().success(MpinResponse);
    }


    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GeneralResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = customerAuthService.login(loginRequest);
        return new GeneralResponse<LoginResponse>().success(loginResponse);
    }
}
