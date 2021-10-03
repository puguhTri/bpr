package com.mib.customer.bprcustomersvc.controller;


import com.mib.customer.bprcustomersvc.dto.response.GeneralResponse;
import com.mib.customer.bprcustomersvc.dto.response.ProfileResponse;
import com.mib.customer.bprcustomersvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/customer/")
@AllArgsConstructor
public class CustomerController {

    private final Environment environment;
    private final CustomerService customerService;

    @GetMapping("/status/check")
    public ResponseEntity<String> status() {
        return ResponseEntity.status(HttpStatus.OK).body("Working..." + environment.getProperty("local.server.port") + "--"
                + environment.getProperty("spring.cloud.client.ip-address"));
    }


    @GetMapping(value = "/profile")
    public GeneralResponse<ProfileResponse> profile(@RequestHeader(value = "Customer-Id") UUID customerId) {
        var profile = customerService.getProfile(customerId);
        return new GeneralResponse<ProfileResponse>().success(profile);
    }

}
