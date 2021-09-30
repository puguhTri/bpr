package com.mib.customer.bprcustomersvc.services;

import com.mib.customer.bprcustomersvc.dto.request.RegisterRequest;
import com.mib.customer.bprcustomersvc.dto.response.RegisterResponse;
import com.mib.customer.bprcustomersvc.mapper.CustomerMapper;
import com.mib.customer.bprcustomersvc.repositories.CustomerRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class CustomerAuthService {

    private final CustomerMapper customerMapper;
    private final CustomerRepo customerRepo;

    public RegisterResponse register(RegisterRequest registerRequest) {
        var customerEntity = customerMapper.toCustomer(registerRequest);
        var customerResponse = customerRepo.save(customerEntity);
        return RegisterResponse.builder()
                .email(customerResponse.getEmail())
                .phoneNumber(customerResponse.getPhoneNumber())
                .otp("123456")
                .build();
    }


}
