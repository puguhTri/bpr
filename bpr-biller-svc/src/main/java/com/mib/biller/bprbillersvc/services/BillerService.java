package com.mib.biller.bprbillersvc.services;


import com.mib.biller.bprbillersvc.constants.BillerTypeConstant;
import com.mib.biller.bprbillersvc.dto.request.middleware.BillerInquiryRequest;
import com.mib.biller.bprbillersvc.dto.request.mobile.PlnBillerRequest;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerInquiryResponse;
import com.mib.biller.bprbillersvc.integration.middleware.InquiryMiddleware;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class BillerService {

    private final InquiryMiddleware inquiryMiddleware;

    public BillerInquiryResponse plnBillerInquiry(PlnBillerRequest billerPlnRequest) {
        /** todo convert request mobile to request middleware*/

        var billerInquiryRequest = converterRequest(billerPlnRequest.getNoPel(), billerPlnRequest.getType());
        var plnInquiryResponse = inquiryMiddleware.processor(billerInquiryRequest);
        return plnInquiryResponse;
    }

    private BillerInquiryRequest converterRequest(String data, int type) {
        StringBuilder private48Build = new StringBuilder();
        switch (type) {
            case BillerTypeConstant.PLN_POSTPAID:
                private48Build.append(1); //type transaction
                private48Build.append(1); //type amount
                break;
            case BillerTypeConstant.PLN_PREPAID:
                private48Build.append(0); //type transaction
                private48Build.append(1); //type amount
                break;
            default:
                break;
        }


        private48Build.append(data); //id pelanggan
        private48Build.append("||||");

        String private48 = private48Build.toString();

        return BillerInquiryRequest.builder()
                .private48(private48)
                .build();
    }

}
