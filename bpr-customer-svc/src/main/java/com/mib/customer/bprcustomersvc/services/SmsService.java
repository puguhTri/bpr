package com.mib.customer.bprcustomersvc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@Log4j2
public class SmsService {


    public void sendOtp(String otp, String phone) throws IOException {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyHHmmss");

        String transId = myDateObj.format(myFormatObj);

        String uri = "http://203.166.197.162/ApiDM/receive.php?uid=mitraIntiOTP&pwd=Mvwa2jKrAuQMHDqywJRoY&msisdn=" + phone + "&sms=Kode+otp+anda+" + otp + "&transid=" + transId + "&smstype=0&sc=EasyNote";
        log.info("uri ::: " + uri);

        ObjectMapper mapper = new ObjectMapper();

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(uri);

            HttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            Document doc = null;
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                try {
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    doc = builder.parse(entity.getContent());
                    log.info("msg ::: " + doc.getDocumentElement().toString());
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
