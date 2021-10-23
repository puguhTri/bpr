package com.mib.customer.bprcustomersvc.controller.web;


import com.mib.customer.bprcustomersvc.dto.response.CustomerWebResponse;
import com.mib.customer.bprcustomersvc.dto.response.DetailCustomerResponse;
import com.mib.customer.bprcustomersvc.dto.response.GeneralResponse;
import com.mib.customer.bprcustomersvc.dto.response.ProfileResponse;
import com.mib.customer.bprcustomersvc.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/web/customer/")
@AllArgsConstructor
public class CustomerWebController {

    private final Environment environment;
    private final CustomerService customerService;


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
}
