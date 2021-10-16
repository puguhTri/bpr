package com.mib.biller.bprbillersvc.controller;


import com.mib.biller.bprbillersvc.dto.request.mobile.PlnBillerInquiryRequest;
import com.mib.biller.bprbillersvc.dto.request.mobile.PlnBillerPaymentRequest;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerInquiryResponse;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerPaymentResponse;
import com.mib.biller.bprbillersvc.dto.response.mobile.BillerInquiryResponseMB;
import com.mib.biller.bprbillersvc.dto.response.mobile.GeneralResponse;
import com.mib.biller.bprbillersvc.services.BillerService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/pln/")
public class PlnBillerController {

    private final Environment environment;
    private final BillerService billerService;

    @GetMapping("/status/check")
    public ResponseEntity<String> status() {
        return ResponseEntity.status(HttpStatus.OK).body("Working..." + environment.getProperty("local.server.port") + "--"
                + environment.getProperty("spring.cloud.client.ip-address"));
    }

    @PostMapping(value = "/inquiry", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<BillerInquiryResponseMB> inquiry(@RequestHeader(value = "Customer-Id") UUID customerId,@Valid @RequestBody PlnBillerInquiryRequest plnBillerRequest) {
        var billerInquiryRes = billerService.plnBillerInquiry(plnBillerRequest, customerId);
        return new GeneralResponse<BillerInquiryResponseMB>().success(billerInquiryRes);
    }

    @PostMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<BillerPaymentResponse> payment(@RequestHeader(value = "Customer-Id") UUID customerId, @RequestBody PlnBillerPaymentRequest plnBillerPaymentRequest) {
        var billerPaymentRes = billerService.plnBillerPayment(plnBillerPaymentRequest, customerId);
        return new GeneralResponse<BillerPaymentResponse>().success(billerPaymentRes);
    }

}
