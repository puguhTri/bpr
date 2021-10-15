package com.mib.biller.bprbillersvc.controller;

import com.mib.biller.bprbillersvc.dto.request.mobile.JackOneBillerInquiryRequest;
import com.mib.biller.bprbillersvc.dto.request.mobile.JackOneBillerPaymentRequest;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerInquiryResponse;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerPaymentResponse;
import com.mib.biller.bprbillersvc.dto.response.mobile.GeneralResponse;
import com.mib.biller.bprbillersvc.services.BillerService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/jackone/")
@AllArgsConstructor
public class JackOneBillerController {

    private final BillerService billerService;

    @PostMapping(value = "/inquiry", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<BillerInquiryResponse> inquiry(@RequestHeader(value = "Customer-Id") UUID customerId, @RequestBody JackOneBillerInquiryRequest jackoneBillerRequest) {
        var billerInquiryRes = billerService.jackoneBillerInquiry(jackoneBillerRequest);
        return new GeneralResponse<BillerInquiryResponse>().success(billerInquiryRes);
    }

    @PostMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<BillerPaymentResponse> payment(@RequestHeader(value = "Customer-Id") UUID customerId, @RequestBody JackOneBillerPaymentRequest jackoneBillerPaymentRequest) {
        var billerPaymentRes = billerService.jackoneBillerPayment(jackoneBillerPaymentRequest);
        return new GeneralResponse<BillerPaymentResponse>().success(billerPaymentRes);
    }
}
