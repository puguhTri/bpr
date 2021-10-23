package com.mib.customer.bprcustomersvc.controller.mobile;


import com.mib.customer.bprcustomersvc.dto.request.CheckMpinRequest;
import com.mib.customer.bprcustomersvc.dto.response.CheckMpinResponse;
import com.mib.customer.bprcustomersvc.dto.response.GeneralResponse;
import com.mib.customer.bprcustomersvc.dto.response.ProfileResponse;
import com.mib.customer.bprcustomersvc.exceptions.FlowException;
import com.mib.customer.bprcustomersvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/mobile/customer/")
@AllArgsConstructor
public class CustomerMobileController {

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

    @PostMapping(value = "/check-mpin")
    public GeneralResponse<CheckMpinResponse> checkOtp(@RequestHeader(value = "Customer-Id") UUID customerId, @RequestBody CheckMpinRequest checkMpinRequest) {
        if (!customerId.equals(checkMpinRequest.getCustomerId())){
            throw new FlowException("Customer Id tidak valid");
        }
        var checkMpin = customerService.chekMpin(checkMpinRequest);
        return new GeneralResponse<CheckMpinResponse>().success(checkMpin);
    }


}
