package com.mib.biller.bprbillersvc.integration.middleware;

import com.mib.biller.bprbillersvc.dto.request.middleware.BillerInquiryRequest;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerInquiryResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
@AllArgsConstructor
public class InquiryMiddleware {

    private final RestTemplate restTemplate;

    public BillerInquiryResponse processor(BillerInquiryRequest billerInquiryRequest) {
        return BillerInquiryResponse.builder().build();
    }

}
