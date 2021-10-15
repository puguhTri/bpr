package com.mib.biller.bprbillersvc.services;


import com.mib.biller.bprbillersvc.constants.BillerTypeConstant;
import com.mib.biller.bprbillersvc.dto.request.middleware.BillerInquiryRequest;
import com.mib.biller.bprbillersvc.dto.request.middleware.BillerPaymentRequest;
import com.mib.biller.bprbillersvc.dto.request.mobile.*;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerInquiryResponse;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerPaymentResponse;
import com.mib.biller.bprbillersvc.dto.response.mobile.BillerInquiryResponseMB;
import com.mib.biller.bprbillersvc.entities.Transaction;
import com.mib.biller.bprbillersvc.integration.middleware.InquiryMiddleware;
import com.mib.biller.bprbillersvc.repositories.TransactionRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
@AllArgsConstructor
public class BillerService {

    private final InquiryMiddleware inquiryMiddleware;
    private final TransactionRepo transactionRepo;

    public BillerInquiryResponseMB plnBillerInquiry(PlnBillerInquiryRequest billerPlnRequest, UUID customerId) {
        /** todo convert request mobile to request middleware*/

        var billerInquiryRequest = converterRequest(billerPlnRequest.getClientId(), billerPlnRequest.getType());
        var plnInquiryResponse = inquiryMiddleware.inquiryProcessor(billerInquiryRequest);


        var newTransPln = createTransaction(customerId, billerPlnRequest.getType(), billerPlnRequest.getClientId(), plnInquiryResponse);

        BillerInquiryResponseMB billerInquiryPlnResponseMB = new BillerInquiryResponseMB();
        billerInquiryPlnResponseMB.setInquiry(plnInquiryResponse);
        billerInquiryPlnResponseMB.setCode(newTransPln.getCode());

        return billerInquiryPlnResponseMB;
    }

    public BillerPaymentResponse plnBillerPayment(PlnBillerPaymentRequest plnBillerPaymentRequest, UUID customerId) {
        var dataInquiryPln = transactionRepo.findByCustomerIdAndCode(customerId, plnBillerPaymentRequest.getCode()).orElseThrow();
        var billerInquiryResponse = dataInquiryPln.getInquiry();

        var billerPaymentRequest = setBillerPaymentRequest(billerInquiryResponse);
        var plnPaymentResponse = inquiryMiddleware.paymentProcessor(billerPaymentRequest);

        dataInquiryPln.setPayment(plnPaymentResponse);
        transactionRepo.save(dataInquiryPln);

        return plnPaymentResponse;
    }


    public BillerInquiryResponse pulsaBillerInquiry(PulsaBillerInquiryRequest pulsaBillerInquiryRequest) {
        return BillerInquiryResponse.builder().build();
    }

    public BillerPaymentResponse pulsaBillerPayment(PulsaBillerPaymentRequest pulsaBillerPaymentRequest) {
        return BillerPaymentResponse.builder().build();
    }


    public BillerInquiryResponseMB pdamBillerInquiry(PdamBillerInquiryRequest pdamBillerInquiryRequest, UUID customerId) {
        var billerInquiryRequest = converterRequest(pdamBillerInquiryRequest.getClientId(), pdamBillerInquiryRequest.getType());
        var pdamInquiryResponse = inquiryMiddleware.inquiryProcessor(billerInquiryRequest);

        var newTransPdam = createTransaction(customerId, pdamBillerInquiryRequest.getType(), pdamBillerInquiryRequest.getClientId(), pdamInquiryResponse);

        BillerInquiryResponseMB billerInquiryPdamResponseMB = new BillerInquiryResponseMB();
        billerInquiryPdamResponseMB.setInquiry(pdamInquiryResponse);
        billerInquiryPdamResponseMB.setCode(newTransPdam.getCode());

        return billerInquiryPdamResponseMB;
    }

    public BillerPaymentResponse pdamBillerPayment(PdamBillerPaymentRequest pdamBillerPaymentRequest, UUID customerId) {
        var dataInquiryPdam = transactionRepo.findByCustomerIdAndCode(customerId, pdamBillerPaymentRequest.getCode()).orElseThrow();
        var billerInquiryResponse = dataInquiryPdam.getInquiry();

        var billerPaymentRequest = setBillerPaymentRequest(billerInquiryResponse);
        var pdamPaymentResponse = inquiryMiddleware.paymentProcessor(billerPaymentRequest);

        dataInquiryPdam.setPayment(pdamPaymentResponse);
        transactionRepo.save(dataInquiryPdam);
        return pdamPaymentResponse;
    }


    public BillerInquiryResponse kaiBillerInquiry(KaiBillerInquiryRequest pdamBillerInquiryRequest) {
        return BillerInquiryResponse.builder().build();
    }

    public BillerPaymentResponse kaiBillerPayment(KaiBillerPaymentRequest pdamBillerPaymentRequest) {
        return BillerPaymentResponse.builder().build();
    }

    public BillerInquiryResponse telkomBillerInquiry(TelkomBillerInquiryRequest pdamBillerInquiryRequest) {
        return BillerInquiryResponse.builder().build();
    }

    public BillerPaymentResponse telkomBillerPayment(TelkomBillerPaymentRequest pdamBillerPaymentRequest) {
        return BillerPaymentResponse.builder().build();
    }

    public BillerInquiryResponse jackoneBillerInquiry(JackOneBillerInquiryRequest jackOneBillerInquiryRequest) {
        return BillerInquiryResponse.builder().build();
    }

    public BillerPaymentResponse jackoneBillerPayment(JackOneBillerPaymentRequest jackOneBillerPaymentRequest) {
        return BillerPaymentResponse.builder().build();
    }


    /**
     * private
     */

    private Transaction createTransaction(UUID customerId, String type, String data, BillerInquiryResponse billerInquiryResponse) {
        Transaction transaction = new Transaction();
        transaction.setCode(UUID.randomUUID());
        transaction.setCustomerId(customerId);
        transaction.setType(type);
        transaction.setIdPelanggan(data);
        transaction.setInquiry(billerInquiryResponse);
        return transactionRepo.save(transaction);
    }

    private BillerPaymentRequest setBillerPaymentRequest(BillerInquiryResponse billerInquiryResponse) {
        var billerPaymentRequest = BillerPaymentRequest.builder()
                .stan(billerInquiryResponse.getSTAN())
                .retrievalReferenceNumber(billerInquiryResponse.getRETRIEVAL_REFERENCE_NUMBER())
                .amount(billerInquiryResponse.getAMOUNT())
                .transmissionDatetime(billerInquiryResponse.getTRANSMISSION_DATETIME()) // generate by system
                .private48(billerInquiryResponse.getPRIVATE_48())
                .private59(billerInquiryResponse.getPRIVATE_59())
                .private63(billerInquiryResponse.getPRIVATE_63())
                .productCode(billerInquiryResponse.getPRODUCT_CODE())
                .descAccount(billerInquiryResponse.getDEST_ACCOUNT())
                .feeAccount(billerInquiryResponse.getFEE_ACCOUNT())
                .srcAccount("10120012815")// how
                .build();

        return billerPaymentRequest;
    }

    private BillerInquiryRequest converterRequest(String data, String type) {
        StringBuilder private48Build = new StringBuilder();

        if (type.equals(BillerTypeConstant.PLN_POSTPAID) || type.equals(BillerTypeConstant.AETRA) || type.equals(BillerTypeConstant.PALYJA)) {
            private48Build.append(1); //type transaction
            private48Build.append(1); //type amount
        } else if (type.equals(BillerTypeConstant.PLN_PREPAID)) {
            private48Build.append(0); //type transaction
            private48Build.append(1); //type amount
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
