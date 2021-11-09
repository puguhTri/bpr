package com.mib.customer.bprcustomersvc.controller.web;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mib.customer.bprcustomersvc.dto.response.CustomerWebResponse;
import com.mib.customer.bprcustomersvc.dto.response.DetailCustomerResponse;
import com.mib.customer.bprcustomersvc.dto.response.GeneralResponse;
import com.mib.customer.bprcustomersvc.services.CustomerService;
import com.mib.customer.bprcustomersvc.services.SmsService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/web/customer/")
@AllArgsConstructor
@Log4j2
public class CustomerWebController {

    private final Environment environment;
    private final CustomerService customerService;
    private final SmsService smsService;


    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GeneralResponse<Page<CustomerWebResponse>> list(Pageable pageable) {
        Page<CustomerWebResponse> result = customerService.listCustomer(pageable);
        return new GeneralResponse<Page<CustomerWebResponse>>().success(result);
    }


    @GetMapping(value = "/{customer-id}/detail", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GeneralResponse<DetailCustomerResponse> detail(@PathVariable("customer-id") UUID customerId) {
        var customer = customerService.detailCustomer(customerId);
        return new GeneralResponse<DetailCustomerResponse>().success(customer);
    }


    @PostMapping(value = "/{phone}/{otp}/test-otp", produces = MediaType.APPLICATION_JSON_VALUE)
    public String testOtp(@PathVariable("phone") String phone, @PathVariable("otp") String otp) throws IOException, InterruptedException {
        smsService.sendOtp(otp, phone);
        return "Test sukses";
    }
}
