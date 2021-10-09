package com.mib.biller.bprbillersvc.services;


import com.mib.biller.bprbillersvc.constants.BillerTypeConstant;
import com.mib.biller.bprbillersvc.dto.request.middleware.BillerInquiryRequest;
import com.mib.biller.bprbillersvc.dto.request.mobile.PlnBillerInquiryRequest;
import com.mib.biller.bprbillersvc.dto.request.mobile.PlnBillerPaymentRequest;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerInquiryResponse;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerPaymentResponse;
import com.mib.biller.bprbillersvc.integration.middleware.InquiryMiddleware;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class BillerService {

    private final InquiryMiddleware inquiryMiddleware;

    public BillerInquiryResponse plnBillerInquiry(PlnBillerInquiryRequest billerPlnRequest) {
        /** todo convert request mobile to request middleware*/

        var billerInquiryRequest = converterRequest(billerPlnRequest.getNoPel(), billerPlnRequest.getType());
        var plnInquiryResponse = inquiryMiddleware.processor(billerInquiryRequest);
        return plnInquiryResponse;
    }

    public BillerPaymentResponse plnBillerPayment(PlnBillerPaymentRequest plnBillerPaymentRequest) {
        return BillerPaymentResponse.builder().build();
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

        String stan = "101010";
        String retrievalReferenceNumber = "100100007007";
        String transmissionDatetime = "0929141349";
        String amount = "000000000000";
        String private48 = private48Build.toString();
        String productCode = "100008";

        return BillerInquiryRequest.builder()
                .stan(stan)
                .retrievalReferenceNumber(retrievalReferenceNumber)
                .transmissionDatetime(transmissionDatetime)
                .amount(amount)
                .private48(private48)
                .productCode(productCode)
                .build();
    }

}
