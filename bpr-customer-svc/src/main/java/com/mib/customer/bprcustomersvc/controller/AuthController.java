package com.mib.customer.bprcustomersvc.controller;

import com.mib.customer.bprcustomersvc.dto.request.PasswordSettingRequest;
import com.mib.customer.bprcustomersvc.dto.request.RegisterRequest;
import com.mib.customer.bprcustomersvc.dto.request.VerifyRequest;
import com.mib.customer.bprcustomersvc.dto.response.GeneralResponse;
import com.mib.customer.bprcustomersvc.dto.response.PasswordSettingResponse;
import com.mib.customer.bprcustomersvc.dto.response.RegisterResponse;
import com.mib.customer.bprcustomersvc.dto.response.VerifyResponse;
import com.mib.customer.bprcustomersvc.services.CustomerAuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
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
}
