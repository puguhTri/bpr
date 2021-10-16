package com.mib.biller.bprbillersvc.controller;

import com.mib.biller.bprbillersvc.dto.request.mobile.KaiBillerInquiryRequest;
import com.mib.biller.bprbillersvc.dto.request.mobile.KaiBillerPaymentRequest;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerInquiryResponse;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerPaymentResponse;
import com.mib.biller.bprbillersvc.dto.response.mobile.BillerInquiryResponseMB;
import com.mib.biller.bprbillersvc.dto.response.mobile.GeneralResponse;
import com.mib.biller.bprbillersvc.services.BillerService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/kai/")
@AllArgsConstructor
public class KaiBillerController {

    private final BillerService billerService;

    @PostMapping(value = "/inquiry", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<BillerInquiryResponseMB> inquiry(@RequestHeader(value = "Customer-Id") UUID customerId,@Valid @RequestBody KaiBillerInquiryRequest kaiBillerRequest) {
        var billerInquiryRes = billerService.kaiBillerInquiry(kaiBillerRequest, customerId);
        return new GeneralResponse<BillerInquiryResponseMB>().success(billerInquiryRes);
    }

    @PostMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
    public GeneralResponse<BillerPaymentResponse> payment(@RequestHeader(value = "Customer-Id") UUID customerId, @RequestBody KaiBillerPaymentRequest kaiBillerPaymentRequest) {
        var billerPaymentRes = billerService.kaiBillerPayment(kaiBillerPaymentRequest, customerId);
        return new GeneralResponse<BillerPaymentResponse>().success(billerPaymentRes);
    }
}
