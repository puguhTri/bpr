package com.mib.biller.bprbillersvc.services;


import com.mib.biller.bprbillersvc.constants.BillerTypeConstant;
import com.mib.biller.bprbillersvc.dto.request.middleware.BillerInquiryRequest;
import com.mib.biller.bprbillersvc.dto.request.middleware.BillerPaymentRequest;
import com.mib.biller.bprbillersvc.dto.request.mobile.*;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerInquiryResponse;
import com.mib.biller.bprbillersvc.dto.response.middleware.BillerPaymentResponse;
import com.mib.biller.bprbillersvc.dto.response.mobile.BillerInquiryResponseMB;
import com.mib.biller.bprbillersvc.entities.Transaction;
import com.mib.biller.bprbillersvc.exceptions.FlowException;
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
        if (billerPlnRequest.getType().equals(BillerTypeConstant.PLN_POSTPAID) || billerPlnRequest.getType().equals(BillerTypeConstant.PLN_PREPAID)) {
            var billerInquiryRequest = converterRequest(billerPlnRequest.getClientId(), billerPlnRequest.getType());
            var plnInquiryResponse = inquiryMiddleware.inquiryProcessor(billerInquiryRequest);
            var newTransPln = createTransaction(customerId, billerPlnRequest.getType(), billerPlnRequest.getClientId(), plnInquiryResponse);

            BillerInquiryResponseMB billerInquiryPlnResponseMB = new BillerInquiryResponseMB();
            billerInquiryPlnResponseMB.setInquiry(plnInquiryResponse);
            billerInquiryPlnResponseMB.setCode(newTransPln.getCode());

            return billerInquiryPlnResponseMB;
        } else {
            throw new FlowException("Type Transaksi Salah");
        }


    }

    public BillerPaymentResponse plnBillerPayment(PlnBillerPaymentRequest plnBillerPaymentRequest, UUID customerId) {
        var dataInquiryPln = transactionRepo.findByCustomerIdAndCode(customerId, plnBillerPaymentRequest.getCode()).orElseThrow();
        if (dataInquiryPln.getType().equals(BillerTypeConstant.PLN_POSTPAID) || dataInquiryPln.getType().equals(BillerTypeConstant.PLN_PREPAID)) {
            if (dataInquiryPln.getStatus().equals(BillerTypeConstant.STATUS_INQUIRY)) {
                var billerInquiryResponse = dataInquiryPln.getInquiry();

                var billerPaymentRequest = setBillerPaymentRequest(billerInquiryResponse);
                var plnPaymentResponse = inquiryMiddleware.paymentProcessor(billerPaymentRequest);

                dataInquiryPln.setPayment(plnPaymentResponse);
                dataInquiryPln.setStatus(BillerTypeConstant.STATUS_PAYMENT);
                transactionRepo.save(dataInquiryPln);

                return plnPaymentResponse;
            } else {
                throw new FlowException("Transaksi telah Selesai, tidak dapat dilanjutkan");
            }
        } else {
            throw new FlowException("Type Transaksi Salah");
        }


    }


    public BillerInquiryResponseMB pulsaBillerInquiry(PulsaBillerInquiryRequest pulsaBillerInquiryRequest, UUID customerId) {
        if (pulsaBillerInquiryRequest.getType().equals(BillerTypeConstant.XL_POSTPAID) || pulsaBillerInquiryRequest.getType().equals(BillerTypeConstant.XL_PREPAID)
                || pulsaBillerInquiryRequest.getType().equals(BillerTypeConstant.THREE_POSTPAID) || pulsaBillerInquiryRequest.getType().equals(BillerTypeConstant.THREE_PREPAID)
                | pulsaBillerInquiryRequest.getType().equals(BillerTypeConstant.SMARTFREN_POSTPAID) || pulsaBillerInquiryRequest.getType().equals(BillerTypeConstant.SMARTFREN_PREPAID)
                || pulsaBillerInquiryRequest.getType().equals(BillerTypeConstant.TELKOMSEL_POSTPAID) || pulsaBillerInquiryRequest.getType().equals(BillerTypeConstant.TELKOMSEL_PREPAID)
                || pulsaBillerInquiryRequest.getType().equals(BillerTypeConstant.INDOSAT_POSTPAID) || pulsaBillerInquiryRequest.getType().equals(BillerTypeConstant.INDOSAT_PREPAID)) {
            var billerInquiryRequest = converterRequest(pulsaBillerInquiryRequest.getPhoneNumber(), pulsaBillerInquiryRequest.getType());
            var pulsaInquiryResponse = inquiryMiddleware.inquiryProcessor(billerInquiryRequest);
            var newTransPulsa = createTransaction(customerId, pulsaBillerInquiryRequest.getType(), pulsaBillerInquiryRequest.getPhoneNumber(), pulsaInquiryResponse);

            BillerInquiryResponseMB billerInquiryPulsaResponseMB = new BillerInquiryResponseMB();
            billerInquiryPulsaResponseMB.setInquiry(pulsaInquiryResponse);
            billerInquiryPulsaResponseMB.setCode(newTransPulsa.getCode());

            return billerInquiryPulsaResponseMB;
        } else {
            throw new FlowException("Type Transaksi Salah");
        }

    }

    public BillerPaymentResponse pulsaBillerPayment(PulsaBillerPaymentRequest pulsaBillerPaymentRequest, UUID customerId) {
        var dataInquiryPulsa = transactionRepo.findByCustomerIdAndCode(customerId, pulsaBillerPaymentRequest.getCode()).orElseThrow();
        if (dataInquiryPulsa.getType().equals(BillerTypeConstant.XL_POSTPAID) || dataInquiryPulsa.getType().equals(BillerTypeConstant.XL_PREPAID)
                || dataInquiryPulsa.getType().equals(BillerTypeConstant.THREE_POSTPAID) || dataInquiryPulsa.getType().equals(BillerTypeConstant.THREE_PREPAID)
                || dataInquiryPulsa.getType().equals(BillerTypeConstant.SMARTFREN_POSTPAID) || dataInquiryPulsa.getType().equals(BillerTypeConstant.SMARTFREN_PREPAID)
                || dataInquiryPulsa.getType().equals(BillerTypeConstant.TELKOMSEL_POSTPAID) || dataInquiryPulsa.getType().equals(BillerTypeConstant.TELKOMSEL_PREPAID)
                || dataInquiryPulsa.getType().equals(BillerTypeConstant.INDOSAT_POSTPAID) || dataInquiryPulsa.getType().equals(BillerTypeConstant.INDOSAT_PREPAID)) {
            if (dataInquiryPulsa.getStatus().equals(BillerTypeConstant.STATUS_INQUIRY)) {
                var billerInquiryResponse = dataInquiryPulsa.getInquiry();

                var billerPaymentRequest = setBillerPaymentRequest(billerInquiryResponse);
                var pulsaPaymentResponse = inquiryMiddleware.paymentProcessor(billerPaymentRequest);

                dataInquiryPulsa.setPayment(pulsaPaymentResponse);
                dataInquiryPulsa.setStatus(BillerTypeConstant.STATUS_PAYMENT);
                transactionRepo.save(dataInquiryPulsa);

                return pulsaPaymentResponse;
            } else {
                throw new FlowException("Transaksi telah Selesai, tidak dapat dilanjutkan");
            }
        } else {
            throw new FlowException("Type Transaksi Salah");
        }
    }


    public BillerInquiryResponseMB pdamBillerInquiry(PdamBillerInquiryRequest pdamBillerInquiryRequest, UUID customerId) {
        if (pdamBillerInquiryRequest.getType().equals(BillerTypeConstant.PALYJA) || pdamBillerInquiryRequest.getType().equals(BillerTypeConstant.AETRA)) {
            var billerInquiryRequest = converterRequest(pdamBillerInquiryRequest.getClientId(), pdamBillerInquiryRequest.getType());
            var pdamInquiryResponse = inquiryMiddleware.inquiryProcessor(billerInquiryRequest);
            var newTransPdam = createTransaction(customerId, pdamBillerInquiryRequest.getType(), pdamBillerInquiryRequest.getClientId(), pdamInquiryResponse);

            BillerInquiryResponseMB billerInquiryPdamResponseMB = new BillerInquiryResponseMB();
            billerInquiryPdamResponseMB.setInquiry(pdamInquiryResponse);
            billerInquiryPdamResponseMB.setCode(newTransPdam.getCode());

            return billerInquiryPdamResponseMB;
        } else {
            throw new FlowException("Type Transaksi Salah");
        }


    }

    public BillerPaymentResponse pdamBillerPayment(PdamBillerPaymentRequest pdamBillerPaymentRequest, UUID customerId) {
        var dataInquiryPdam = transactionRepo.findByCustomerIdAndCode(customerId, pdamBillerPaymentRequest.getCode()).orElseThrow();
        if (dataInquiryPdam.getType().equals(BillerTypeConstant.PALYJA) || dataInquiryPdam.getType().equals(BillerTypeConstant.AETRA)) {
            if (dataInquiryPdam.getStatus().equals(BillerTypeConstant.STATUS_INQUIRY)) {
                var billerInquiryResponse = dataInquiryPdam.getInquiry();

                var billerPaymentRequest = setBillerPaymentRequest(billerInquiryResponse);
                var pdamPaymentResponse = inquiryMiddleware.paymentProcessor(billerPaymentRequest);

                dataInquiryPdam.setPayment(pdamPaymentResponse);
                transactionRepo.save(dataInquiryPdam);
                return pdamPaymentResponse;
            } else {
                throw new FlowException("Transaksi telah Selesai, tidak dapat dilanjutkan");
            }
        } else {
            throw new FlowException("Type Transaksi Salah");
        }
    }


    public BillerInquiryResponseMB kaiBillerInquiry(KaiBillerInquiryRequest kaiBillerInquiryRequest, UUID customerId) {
        if (kaiBillerInquiryRequest.getType().equals(BillerTypeConstant.KAI)) {
            var billerInquiryRequest = converterRequest(kaiBillerInquiryRequest.getPayCode(), kaiBillerInquiryRequest.getType());
            var kaiInquiryResponse = inquiryMiddleware.inquiryProcessor(billerInquiryRequest);

            var newTransKai = createTransaction(customerId, kaiBillerInquiryRequest.getType(), kaiBillerInquiryRequest.getPayCode(), kaiInquiryResponse);

            BillerInquiryResponseMB billerInquiryPdamResponseMB = new BillerInquiryResponseMB();
            billerInquiryPdamResponseMB.setInquiry(kaiInquiryResponse);
            billerInquiryPdamResponseMB.setCode(newTransKai.getCode());
            return billerInquiryPdamResponseMB;
        } else {
            throw new FlowException("Type Transaksi Salah");
        }

    }

    public BillerPaymentResponse kaiBillerPayment(KaiBillerPaymentRequest pdamBillerPaymentRequest, UUID customerId) {
        var dataInquiryKai = transactionRepo.findByCustomerIdAndCode(customerId, pdamBillerPaymentRequest.getCode()).orElseThrow();
        if (dataInquiryKai.getType().equals(BillerTypeConstant.KAI)) {
            if (dataInquiryKai.getStatus().equals(BillerTypeConstant.STATUS_INQUIRY)) {
                var billerInquiryResponse = dataInquiryKai.getInquiry();

                var billerPaymentRequest = setBillerPaymentRequest(billerInquiryResponse);
                var kaiPaymentResponse = inquiryMiddleware.paymentProcessor(billerPaymentRequest);

                dataInquiryKai.setPayment(kaiPaymentResponse);
                transactionRepo.save(dataInquiryKai);
                return kaiPaymentResponse;
            } else {
                throw new FlowException("Transaksi telah Selesai, tidak dapat dilanjutkan");
            }
        } else {
            throw new FlowException("Type Transaksi Salah");
        }
    }

    public BillerInquiryResponseMB telkomBillerInquiry(TelkomBillerInquiryRequest telkomBillerInquiryRequest, UUID customerId) {

        if (telkomBillerInquiryRequest.getType().equals(BillerTypeConstant.SPEEDY)) {
            String code = telkomBillerInquiryRequest.getClientId() + telkomBillerInquiryRequest.getArea();
            var billerInquiryRequest = converterRequest(code, telkomBillerInquiryRequest.getType());
            var speedyInquiryResponse = inquiryMiddleware.inquiryProcessor(billerInquiryRequest);

            var newTransSpeedy = createTransaction(customerId, telkomBillerInquiryRequest.getType(), code, speedyInquiryResponse);

            BillerInquiryResponseMB billerInquirySpeedyResponseMB = new BillerInquiryResponseMB();
            billerInquirySpeedyResponseMB.setInquiry(speedyInquiryResponse);
            billerInquirySpeedyResponseMB.setCode(newTransSpeedy.getCode());
            return billerInquirySpeedyResponseMB;
        } else {
            throw new FlowException("Type Transaksi Salah");
        }

    }

    public BillerPaymentResponse telkomBillerPayment(TelkomBillerPaymentRequest telkomBillerPaymentRequest, UUID customerId) {
        var dataInquiryTelkom = transactionRepo.findByCustomerIdAndCode(customerId, telkomBillerPaymentRequest.getCode()).orElseThrow();
        if (dataInquiryTelkom.getType().equals(BillerTypeConstant.SPEEDY)) {
            if (dataInquiryTelkom.getStatus().equals(BillerTypeConstant.STATUS_INQUIRY)) {
                var billerInquiryResponse = dataInquiryTelkom.getInquiry();

                var billerPaymentRequest = setBillerPaymentRequest(billerInquiryResponse);
                var kaiPaymentResponse = inquiryMiddleware.paymentProcessor(billerPaymentRequest);

                dataInquiryTelkom.setPayment(kaiPaymentResponse);
                transactionRepo.save(dataInquiryTelkom);
                return kaiPaymentResponse;
            } else {
                throw new FlowException("Transaksi telah Selesai, tidak dapat dilanjutkan");
            }
        } else {
            throw new FlowException("Type Transaksi Salah");
        }
    }

    public BillerInquiryResponseMB jackoneBillerInquiry(JakoneBillerInquiryRequest jakoneBillerInquiryRequest, UUID customerId) {
        if (jakoneBillerInquiryRequest.getType().equals(BillerTypeConstant.JAKONE)) {

            var billerInquiryRequest = converterRequest(jakoneBillerInquiryRequest.getBill(), jakoneBillerInquiryRequest.getType());
            var jakoneInquiryResponse = inquiryMiddleware.inquiryProcessor(billerInquiryRequest);

            var newTransSpeedy = createTransaction(customerId, jakoneBillerInquiryRequest.getType(), jakoneBillerInquiryRequest.getBill(), jakoneInquiryResponse);

            BillerInquiryResponseMB billerInquirySpeedyResponseMB = new BillerInquiryResponseMB();
            billerInquirySpeedyResponseMB.setInquiry(jakoneInquiryResponse);
            billerInquirySpeedyResponseMB.setCode(newTransSpeedy.getCode());
            return billerInquirySpeedyResponseMB;
        } else {
            throw new FlowException("Type Transaksi Salah");
        }
    }

    public BillerPaymentResponse jackoneBillerPayment(JakoneBillerPaymentRequest jakoneBillerPaymentRequest, UUID customerId) {
        var dataInquiryJakone = transactionRepo.findByCustomerIdAndCode(customerId, jakoneBillerPaymentRequest.getCode()).orElseThrow();
        if (dataInquiryJakone.getType().equals(BillerTypeConstant.JAKONE)) {
            if (dataInquiryJakone.getStatus().equals(BillerTypeConstant.STATUS_INQUIRY)) {
                var billerInquiryResponse = dataInquiryJakone.getInquiry();

                var billerPaymentRequest = setBillerPaymentRequest(billerInquiryResponse);
                var jakPaymentResponse = inquiryMiddleware.paymentProcessor(billerPaymentRequest);

                dataInquiryJakone.setPayment(jakPaymentResponse);
                transactionRepo.save(dataInquiryJakone);
                return jakPaymentResponse;
            } else {
                throw new FlowException("Transaksi telah Selesai, tidak dapat dilanjutkan");
            }
        } else {
            throw new FlowException("Type Transaksi Salah");
        }
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
        transaction.setStatus(BillerTypeConstant.STATUS_INQUIRY);
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

        if (type.equals(BillerTypeConstant.PLN_POSTPAID) || type.equals(BillerTypeConstant.AETRA) || type.equals(BillerTypeConstant.PALYJA)
                || type.equals(BillerTypeConstant.KAI) || type.equals(BillerTypeConstant.XL_POSTPAID) || type.equals(BillerTypeConstant.THREE_POSTPAID)
                || type.equals(BillerTypeConstant.SMARTFREN_POSTPAID) || type.equals(BillerTypeConstant.TELKOMSEL_POSTPAID) || type.equals(BillerTypeConstant.INDOSAT_POSTPAID)
                || type.equals(BillerTypeConstant.SPEEDY)) {
            private48Build.append(1); //type transaction
            private48Build.append(1); //type amount
        } else if (type.equals(BillerTypeConstant.PLN_PREPAID) || type.equals(BillerTypeConstant.XL_PREPAID) || type.equals(BillerTypeConstant.THREE_PREPAID)
                || type.equals(BillerTypeConstant.SMARTFREN_PREPAID) || type.equals(BillerTypeConstant.TELKOMSEL_PREPAID) || type.equals(BillerTypeConstant.INDOSAT_PREPAID)) {
            private48Build.append(0); //type transaction
            private48Build.append(1); //type amount
        } else if (type.equals(BillerTypeConstant.JAKONE)) {
            private48Build.append(1); //type transaction
            private48Build.append(0); //type amount
        } else {
            throw new FlowException("Kode Salah");
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
