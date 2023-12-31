package com.mib.biller.bprbillersvc.integration.middleware;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mib.biller.bprbillersvc.dto.request.middleware.BillerInquiryRequest;
import com.mib.biller.bprbillersvc.dto.request.middleware.BillerPaymentRequest;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerInquiryResponse;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerPaymentResponse;
import com.mib.biller.bprbillersvc.exceptions.FlowException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
@AllArgsConstructor
public class InquiryMiddleware {

    private final RestTemplate restTemplate;
    private final Environment environment;

    public BillerInquiryResponse inquiryProcessor(BillerInquiryRequest billerInquiryRequest) {
        BillerInquiryResponse resBillerInquiry = null;
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        String payload = gson.toJson(billerInquiryRequest);
        log.info("payload :: " + payload);

        /** call api middleware*/
        try {
            String middlewareApiUrl = environment.getProperty("middleware.api.url");
            String endpointBillerInquiry = middlewareApiUrl + "/multibillerbdki/inquiry";


            HttpHeaders headersUpdateStock = new HttpHeaders();
            //headersUpdateStock.setBearerAuth(accessToken);
            headersUpdateStock.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestInquiry = new HttpEntity<>(headersUpdateStock);
            ResponseEntity<String> responseInquiry = restTemplate.exchange(endpointBillerInquiry, HttpMethod.POST, requestInquiry, String.class);


            String res = responseInquiry.getBody().replaceAll("\n", "");
            log.info("response middleware inquiry :: " + res);
            gson = new GsonBuilder().disableHtmlEscaping().create();
            resBillerInquiry = gson.fromJson(res, BillerInquiryResponse.class);
            log.info("res bind :: " + resBillerInquiry.getSTAN());
        } catch (Exception e) {
            throw new FlowException("Gagal koneksi ke middleware");
        }

        return resBillerInquiry;
    }

    public BillerPaymentResponse paymentProcessor(BillerPaymentRequest billerPaymentRequest) {
        BillerPaymentResponse resBillerPayment = null;
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        String payload = gson.toJson(billerPaymentRequest);
        log.info("payload :: " + payload);

        /** call api middleware*/
        try {
            String middlewareApiUrl = environment.getProperty("middleware.api.url");
            String endpointBillerInquiry = middlewareApiUrl + "/multibillerbdki/payment";


            HttpHeaders headersUpdateStock = new HttpHeaders();
            //headersUpdateStock.setBearerAuth(accessToken);
            headersUpdateStock.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestPayment = new HttpEntity<>(headersUpdateStock);
            ResponseEntity<String> responsePayment = restTemplate.exchange(endpointBillerInquiry, HttpMethod.POST, requestPayment, String.class);


            String res = responsePayment.getBody().replaceAll("\n", "").trim();
            log.info("response middleware payment :: " + res);
            gson = new GsonBuilder().disableHtmlEscaping().create();
            resBillerPayment = gson.fromJson(res, BillerPaymentResponse.class);
            log.info("res bind :: " + resBillerPayment.getPRODUCT_CODE());
        } catch (Exception e) {
            throw new FlowException("Gagal koneksi ke middleware");
        }

        return resBillerPayment;
    }
}
