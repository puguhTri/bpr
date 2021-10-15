package com.mib.biller.bprbillersvc.controller;

import com.mib.biller.bprbillersvc.dto.request.mobile.PulsaBillerInquiryRequest;
import com.mib.biller.bprbillersvc.dto.request.mobile.PulsaBillerPaymentRequest;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerInquiryResponse;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerPaymentResponse;
import com.mib.biller.bprbillersvc.dto.response.mobile.GeneralResponse;
import com.mib.biller.bprbillersvc.services.BillerService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/pulsa/")
@AllArgsConstructor
public class PulsaBillerController {

    private final BillerService billerService;

    @PostMapping(value = "/inquiry", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<BillerInquiryResponse> inquiry(@RequestHeader(value = "Customer-Id") UUID customerId, @RequestBody PulsaBillerInquiryRequest pulsaBillerRequest) {
        var billerInquiryRes = billerService.pulsaBillerInquiry(pulsaBillerRequest);
        return new GeneralResponse<BillerInquiryResponse>().success(billerInquiryRes);
    }

    @PostMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<BillerPaymentResponse> payment(@RequestHeader(value = "Customer-Id") UUID customerId, @RequestBody PulsaBillerPaymentRequest pulsaBillerPaymentRequest) {
        var billerPaymentRes = billerService.pulsaBillerPayment(pulsaBillerPaymentRequest);
        return new GeneralResponse<BillerPaymentResponse>().success(billerPaymentRes);
    }
}
