package com.mib.biller.bprbillersvc.integration.middleware;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        String payload = gson.toJson(billerInquiryRequest);
        log.info("payload :: " + payload);


        var res = BillerInquiryResponse.builder().build();

        return BillerInquiryResponse.builder().build();
    }

}
